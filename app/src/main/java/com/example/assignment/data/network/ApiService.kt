package com.example.assignment.data.network

import com.example.assignment.data.repository.RecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class ApiService {
    @Singleton
    @Provides
    fun provideUserRepository(
        api: ApiInterface
    ) = RecordRepository(api)

    @Singleton
    @Provides
    fun providesUserApi(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}