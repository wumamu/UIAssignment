package com.example.assignment.data.repository

import com.example.assignment.data.domain.util.Resource
import com.example.assignment.data.network.ApiInterface
import com.example.assignment.data.network.responses.RecordGetResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import retrofit2.Response

@ActivityScoped
class RecordRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getRecordResponse(): Resource<Response<RecordGetResponse>> {
        val response = try {
            apiInterface.getRecords()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}