package com.nyinyihtunlwin.projects.foodaholic.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.nyinyihtunlwin.projects.foodaholic.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({ startActivity(MainActivity.newInstnace(applicationContext!!)) }, 2000)
    }

}
