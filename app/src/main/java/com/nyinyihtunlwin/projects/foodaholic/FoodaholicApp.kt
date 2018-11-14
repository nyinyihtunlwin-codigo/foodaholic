package com.nyinyihtunlwin.projects.foodaholic

import android.app.Application
import com.nyinyihtunlwin.projects.foodaholic.data.LocalRepository
import com.nyinyihtunlwin.projects.foodaholic.utils.AppUtils

class FoodaholicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalRepository.initLocalRepository(applicationContext)
        AppUtils.initAppUtils(applicationContext)
    }

}