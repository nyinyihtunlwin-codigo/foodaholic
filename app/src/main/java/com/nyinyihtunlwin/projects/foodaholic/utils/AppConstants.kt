package com.nyinyihtunlwin.projects.foodaholic.utils

import com.nyinyihtunlwin.projects.foodaholic.BuildConfig

class AppConstants {
    companion object {
        val BASE_URL = "https://www.themealdb.com/api/json/v1/${BuildConfig.API_KEY}/"
    }
}