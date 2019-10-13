package com.heady.assessment.network

import com.heady.assessment.network.response.ResponseData
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {

    @GET("json")
    fun getData(): Single<ResponseData>
}