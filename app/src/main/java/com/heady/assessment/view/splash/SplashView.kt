package com.heady.assessment.view.splash

import com.heady.assessment.network.response.ResponseData

interface SplashView {
    fun showLoading()
    fun hideLoading()
    fun onSuccess(responseData: ResponseData)
    fun onDataStored(responseData: ResponseData)
    fun onError()
}