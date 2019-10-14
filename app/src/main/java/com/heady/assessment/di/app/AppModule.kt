package com.heady.assessment.di.app

import com.heady.assessment.data.AppDatabase
import com.heady.assessment.data.DataManager
import com.heady.assessment.network.ApiManager
import com.heady.assessment.network.ApiService
import com.heady.assessment.ui.main.MainApplication
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
object AppModule {

    @JvmStatic
    @Provides
    fun provideDataBase(application: MainApplication): AppDatabase {
        DataManager.init(application.applicationContext)
        return DataManager.getDataBase()
    }

    @JvmStatic
    @Provides
    fun provideApiService(): ApiService {
        return ApiManager.getService()
    }

}