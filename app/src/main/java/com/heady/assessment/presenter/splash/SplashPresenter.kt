package com.heady.assessment.presenter.splash

import com.heady.assessment.data.SyncManager
import com.heady.assessment.view.splash.SplashView

interface SplashPresenter {
    fun setView(splashView: SplashView)
    fun fetchData(syncManger: SyncManager)
}