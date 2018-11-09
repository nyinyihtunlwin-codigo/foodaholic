package com.nyinyihtunlwin.projects.foodaholic.delegates

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel

interface CategoryDelegate {
    fun onTapCategory(categoryModel: CategoryModel)
}