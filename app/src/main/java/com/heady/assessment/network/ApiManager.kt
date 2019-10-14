package com.heady.assessment.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiManager {
    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stark-spire-93433.herokuapp.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create<ApiService>(ApiService::class.java)
    }

    fun getService(): ApiService {
        return apiService
    }
}