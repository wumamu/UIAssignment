package com.example.assignment.data.network

import com.example.assignment.data.network.ApiConstant.API_TOKEN
import com.example.assignment.data.network.ApiConstant.END_POINTS
import com.example.assignment.data.network.responses.RecordGetResponse
import javax.inject.Singleton
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Singleton

interface ApiInterface {

    @GET(END_POINTS)
    suspend fun getRecords(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100,
        @Query("api_key") apiKey: String = API_TOKEN
    ): Response<RecordGetResponse>
}