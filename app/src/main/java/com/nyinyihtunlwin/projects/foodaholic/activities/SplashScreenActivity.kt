package com.nyinyihtunlwin.projects.foodaholic.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nyinyihtunlwin.projects.foodaholic.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(MainActivity.newInstnace(applicationContext))
        }, 2000)
    }

}
