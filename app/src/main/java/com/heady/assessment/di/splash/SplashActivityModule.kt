package com.heady.assessment.di.splash

import com.heady.assessment.presenter.splash.SplashPresenter
import com.heady.assessment.presenter.splash.SplashPresenterImpl
import dagger.Module
import dagger.Provides

@Module()
object SplashActivityModule {

    @JvmStatic
    @Provides
    fun provideSplashPresenter(): SplashPresenter {
        return SplashPresenterImpl()
    }


}