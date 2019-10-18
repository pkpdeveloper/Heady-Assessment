package com.heady.assessment.data

import android.content.Context
import com.heady.assessment.network.ApiService
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.util.NetworkUtil
import io.reactivex.Single
import java.lang.ref.WeakReference

class SyncManager(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val context: Context
) {

    companion object {
        private var cacheResponseData: WeakReference<ResponseData>? = null
    }

    fun getData(): Single<ResponseData> {
        val networkObservable = apiService.getData().doAfterSuccess {
            // put copy of data in cache
            cacheResponseData?.clear()
            cacheResponseData = WeakReference(it)

            // store copy of data in database
            appDatabase.responseDao().insertAll(it).subscribe()
        }
        val dataBaseObservable = appDatabase.responseDao().getData().doAfterSuccess {
            // put copy of data in cache
            cacheResponseData?.clear()
            cacheResponseData = WeakReference(it)
        }
        return when {
            // Select default Cache Observable
            cacheResponseData?.get() != null -> getCacheDataObservable()

            // Select network Observable when connected with internet and cacheData is null
            cacheResponseData?.get() == null && NetworkUtil.isConnectedWithInternet(context) -> networkObservable

            // Select database Observable when not connected with internet and cacheData is null
            cacheResponseData?.get() == null && !NetworkUtil.isConnectedWithInternet(context) -> dataBaseObservable
            else -> getCacheDataObservable()
        }
    }

    private fun getCacheDataObservable(): Single<ResponseData> {
        return Single.create<ResponseData> {
            // cache data found return it as result
            if (cacheResponseData?.get() != null && !it.isDisposed) {
                cacheResponseData?.get()?.let { data ->
                    it.onSuccess(data)
                }
            } else if (!it.isDisposed) {
                it.onError(NullPointerException("Response data is null"))
            }
        }
    }
}