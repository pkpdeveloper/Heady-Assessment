package com.heady.assessment.di.main

import com.heady.assessment.ui.main.MainActivity
import com.heady.assessment.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [MainActivityModule::class, MainFragmentModule::class])
abstract class ContributeMainModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeMainFragment(): MainFragment

}