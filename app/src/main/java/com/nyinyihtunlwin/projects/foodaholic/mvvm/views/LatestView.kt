package com.nyinyihtunlwin.projects.foodaholic.mvvm.views

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.sharedmodule.model.BaseView

interface LatestView : BaseView {
    fun onDataLoaded(mealList: List<MealModel>)
    fun onError(message: String)
}