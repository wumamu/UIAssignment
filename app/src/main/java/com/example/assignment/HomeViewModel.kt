package com.example.assignment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.domain.model.Record
import com.example.assignment.data.network.ApiConstant
import com.example.assignment.data.network.ApiService
import com.example.assignment.data.network.model.RecordDtoMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {

    var isLoading = mutableStateOf(false)

    var records: MutableState<List<Record>> = mutableStateOf(listOf())
    private var cacheRecords = listOf<Record>()

    var isSearching = mutableStateOf(false)
    private var isSearchStarting = true

    suspend fun getAllData() {
        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.getRecords()
                if (response.isSuccessful) {
                    isLoading.value = true
                    val converter = RecordDtoMapper()
                    records.value = converter.toDomainList(response.body()!!.records)
                    cacheRecords = records.value
                }
            }
        }
    }

    fun searchByRegion(query: String) {
        val listToSearch = if(isSearchStarting) {
            records.value
        } else {
            cacheRecords
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                records.value = cacheRecords
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.siteName?.contains(query.trim(), ignoreCase = true) == true
            }
            // need to cache list
            if(isSearchStarting) {
                cacheRecords = records.value
                isSearchStarting = false
            }
            records.value = results
            isSearching.value = true
        }
    }
}