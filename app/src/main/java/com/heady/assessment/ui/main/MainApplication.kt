package com.heady.assessment.ui.main

import com.heady.assessment.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MainApplication : DaggerApplication() {
    private val appComponent: AndroidInjector<MainApplication> by lazy {
        DaggerAppComponent
            .builder()
            .create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }


}