package com.nyinyihtunlwin.projects.foodaholic.di

import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.FoodaholicApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var app: FoodaholicApp) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app.applicationContext
    }
}