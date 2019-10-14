package com.heady.assessment.di.app

import com.heady.assessment.di.main.ContributeMainModule
import com.heady.assessment.di.splash.ContributeSplashModule
import com.heady.assessment.ui.main.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@Component(modules = [AppModule::class, ContributeMainModule::class, ContributeSplashModule::class, AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()

}