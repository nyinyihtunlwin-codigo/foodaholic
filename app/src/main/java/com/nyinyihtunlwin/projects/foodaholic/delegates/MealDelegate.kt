package com.nyinyihtunlwin.projects.foodaholic.delegates

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel

interface MealDelegate {
    fun onTapMeal(mealModel: MealModel)
}