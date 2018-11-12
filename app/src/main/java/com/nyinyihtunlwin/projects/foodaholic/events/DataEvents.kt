package com.nyinyihtunlwin.projects.foodaholic.events

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel

class DataEvents {

    class CategoriesLoadedEvent(val loadedCategories: List<CategoryModel>)

    class EmptyDataLoadedEvent(val errorMsg: String? = "No data found!")
}