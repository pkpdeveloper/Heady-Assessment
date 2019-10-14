package com.heady.assessment.di.splash

import com.heady.assessment.ui.splash.SplashActivity
import com.heady.assessment.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [SplashActivityModule::class, SplashFragmentModule::class])
abstract class ContributeSplashModule {

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeSplashFragment(): SplashFragment

}