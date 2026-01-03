package com.example.retrofit_appdhara.di

import com.example.retrofit_appdhara.data.remoteapi.ProductApiService
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object ProductRetroInstance {

    private const val BASE_URL = "http://fakestoreapi.com//"

    @get:Provides
    @Singleton
    val remoteApi : ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}