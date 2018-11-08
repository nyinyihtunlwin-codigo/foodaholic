package com.nyinyihtunlwin.projects.foodaholic.mvvm.views

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel

interface LatestView {
    fun onDataLoaded(mealList: List<MealModel>)
    fun onError(message: String)
}