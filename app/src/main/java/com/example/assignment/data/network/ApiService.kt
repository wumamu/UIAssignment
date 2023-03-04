package com.example.assignment.data.network

import com.example.assignment.data.network.ApiConstant.END_POINTS
import com.example.assignment.data.network.responses.RecordGetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINTS)
    suspend fun getRecords(
        @Query("api_key") apiKey: String
    ): RecordGetResponse

}