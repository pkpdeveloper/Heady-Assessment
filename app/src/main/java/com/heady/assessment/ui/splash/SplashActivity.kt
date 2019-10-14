package com.heady.assessment.ui.splash

import android.os.Bundle
import com.heady.assessment.R
import dagger.android.support.DaggerAppCompatActivity

class SplashActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, SplashFragment())
            .commit()
    }
}
