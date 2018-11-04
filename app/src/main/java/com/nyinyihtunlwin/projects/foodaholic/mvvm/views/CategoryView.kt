package com.nyinyihtunlwin.projects.foodaholic.mvvm.views

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel

interface CategoryView {
    fun onDataLoaded(catList: List<CategoryModel>)
    fun onError(message: String)
    fun onShowLoading()
    fun onDismissLoading()
}