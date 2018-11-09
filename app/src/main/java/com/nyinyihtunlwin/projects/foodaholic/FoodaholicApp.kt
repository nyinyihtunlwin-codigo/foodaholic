package com.nyinyihtunlwin.projects.foodaholic

import android.app.Application
import com.nyinyihtunlwin.projects.foodaholic.data.LocalRepository

class FoodaholicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalRepository.initLocalRepository(applicationContext)
    }

}