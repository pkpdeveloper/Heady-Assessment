package com.heady.assessment.presenter.main

import com.heady.assessment.data.AppDatabase
import com.heady.assessment.view.main.MainView

interface MainPresenter {
    fun setView(mainView: MainView)
    fun loadData(appDatabase: AppDatabase)
}