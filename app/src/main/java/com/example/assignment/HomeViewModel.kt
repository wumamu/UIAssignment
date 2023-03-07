package com.example.assignment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.domain.model.Record
import com.example.assignment.data.domain.util.Resource
import com.example.assignment.data.network.model.RecordDtoMapper
import com.example.assignment.data.repository.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel

class HomeViewModel @Inject constructor(
    private val recordRepository: RecordRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)

    var records: MutableState<List<Record>> = mutableStateOf(listOf())
    private var cacheRecords = listOf<Record>()

    var isSearching = mutableStateOf(false)
    private var isSearchStarting = true

    suspend fun getAllData() {
        val response = recordRepository.getRecordResponse()
        if (response is Resource.Success) {
            isLoading.value = true
            val converter = RecordDtoMapper()
            records.value = converter.toDomainList(response.data?.body()!!.records)
            cacheRecords = records.value
        }
    }

    fun searchByRegion(query: String) {
        val listToSearch = if (isSearchStarting) {
            records.value
        } else {
            cacheRecords
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                records.value = cacheRecords
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.siteName?.contains(query.trim(), ignoreCase = true) == true
            }
            // need to cache list
            if (isSearchStarting) {
                cacheRecords = records.value
                isSearchStarting = false
            }
            records.value = results
            isSearching.value = true
        }
    }
}