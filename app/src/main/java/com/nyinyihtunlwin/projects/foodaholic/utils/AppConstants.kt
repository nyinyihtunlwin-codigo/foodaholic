package com.nyinyihtunlwin.projects.foodaholic.utils

import com.nyinyihtunlwin.projects.foodaholic.BuildConfig

class AppConstants {
    companion object {
        val BASE_URL = "https://www.themealdb.com/api/json/v1/${BuildConfig.API_KEY}/"
        val IMAGE_URL = "https://www.themealdb.com/images/ingredients/"
        const val TABLE_CATEGORIES = "categories"
        const val TABLE_MEALS = "meals"
        const val TABLE_BOOKMARKS = "bookmarks"
    }
}