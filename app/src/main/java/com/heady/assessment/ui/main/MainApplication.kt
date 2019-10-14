package com.heady.assessment.ui.main

import android.app.Application
import com.heady.assessment.data.DataManager


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DataManager.init(this)
    }
}