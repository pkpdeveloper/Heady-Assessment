package com.heady.assessment.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heady.assessment.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, SplashFragment())
            .commit()
    }
}
