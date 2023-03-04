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

    var records: MutableState<List<Record>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.getRecords(apiKey = ApiConstant.API_TOKEN)
                val converter = RecordDtoMapper()
                records.value = converter.toDomainList(response.records)
            }
        }
    }
}