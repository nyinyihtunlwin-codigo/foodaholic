package com.nyinyihtunlwin.projects.foodaholic

import android.app.Application
import com.nyinyihtunlwin.projects.foodaholic.persistence.LocalRepository
import com.nyinyihtunlwin.projects.foodaholic.utils.AppUtils
import com.nyinyihtunlwin.projects.foodaholic.utils.ConfigUtils

class FoodaholicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ConfigUtils.initConfigUtils(applicationContext)
        LocalRepository.initLocalRepository(applicationContext)
        AppUtils.initAppUtils(applicationContext)
    }

}