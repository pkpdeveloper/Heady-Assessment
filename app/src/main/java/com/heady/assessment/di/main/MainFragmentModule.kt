package com.heady.assessment.di.main

import com.heady.assessment.ui.main.ProductAdapter
import dagger.Module
import dagger.Provides

@Module
object MainFragmentModule {

    @JvmStatic
    @Provides
    fun provideProductAdapter(): ProductAdapter {
        return ProductAdapter()
    }
}