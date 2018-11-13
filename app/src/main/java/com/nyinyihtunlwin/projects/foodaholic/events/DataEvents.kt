package com.nyinyihtunlwin.projects.foodaholic.events

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel

class DataEvents {

    class CategoriesLoadedEvent(val loadedCategories: List<CategoryModel>)

    class MealsLoadedEvent(val loadedMeals: List<MealModel>)

    class EmptyDataLoadedEvent(val errorMsg: String? = "No data found!")
}