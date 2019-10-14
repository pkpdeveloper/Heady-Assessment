package com.heady.assessment.presenter.splash

import com.heady.assessment.data.AppDatabase
import com.heady.assessment.network.ApiService
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.view.splash.SplashView

interface SplashPresenter {
    fun setView(splashView: SplashView)
    fun fetchData(apiService: ApiService)
    fun storeData(appDatabase: AppDatabase, responseData: ResponseData)
}