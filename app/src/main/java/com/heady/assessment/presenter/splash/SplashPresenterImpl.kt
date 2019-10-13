package com.heady.assessment.presenter.splash

import com.heady.assessment.data.AppDatabase
import com.heady.assessment.network.ApiService
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.view.splash.SplashView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SplashPresenterImpl : SplashPresenter {
    private lateinit var view: SplashView
    override fun setView(splashView: SplashView) {
        this.view = splashView
    }

    override fun fetchData(apiService: ApiService) {
        apiService.getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseData> {
                override fun onSuccess(responseData: ResponseData) {
                    view.hideLoading()
                    view.onSuccess(responseData)
                }

                override fun onError(e: Throwable) {
                    view.hideLoading()
                    view.onError()
                }

                override fun onSubscribe(d: Disposable) {
                    view.showLoading()
                }
            })


    }

    override fun storeData(appDatabase: AppDatabase, responseData: ResponseData) {
        appDatabase.responseDao().insertAll(responseData).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Long> {
                override fun onSuccess(value: Long) {
                    view.onDataStored(responseData)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    view.onError()
                }

            })
    }
}