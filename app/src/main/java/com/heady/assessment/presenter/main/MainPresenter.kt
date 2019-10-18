package com.heady.assessment.presenter.main

import com.heady.assessment.data.SyncManager
import com.heady.assessment.view.main.MainView

interface MainPresenter {
    fun setView(mainView: MainView)
    fun loadData(syncManager: SyncManager)
}