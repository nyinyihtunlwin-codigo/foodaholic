package com.nyinyihtunlwin.projects.foodaholic

import android.app.Application
import com.nyinyihtunlwin.projects.foodaholic.di.AppComponent
import com.nyinyihtunlwin.projects.foodaholic.di.AppModule
import com.nyinyihtunlwin.projects.foodaholic.di.DaggerAppComponent

class FoodaholicApp : Application() {
    val mComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    var component: AppComponent? = null
        get() = mComponent!!
}