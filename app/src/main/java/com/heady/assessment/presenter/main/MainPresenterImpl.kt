package com.heady.assessment.presenter.main

import com.heady.assessment.data.AppDatabase
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.view.main.MainView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl : MainPresenter {
    private lateinit var view: MainView
    override fun setView(mainView: MainView) {
        this.view = mainView
    }

    override fun loadData(appDatabase: AppDatabase) {
        appDatabase.responseDao().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseData> {
                override fun onSuccess(responseData: ResponseData) {
                    view.displayData(responseData)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    view.onError()
                }

            })
    }
}