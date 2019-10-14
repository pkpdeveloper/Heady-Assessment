package com.heady.assessment.di.main

import com.heady.assessment.presenter.main.MainPresenter
import com.heady.assessment.presenter.main.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module()
object MainActivityModule {

    @JvmStatic
    @Provides
    fun provideMainPresenter(): MainPresenter {
        return MainPresenterImpl()
    }


}