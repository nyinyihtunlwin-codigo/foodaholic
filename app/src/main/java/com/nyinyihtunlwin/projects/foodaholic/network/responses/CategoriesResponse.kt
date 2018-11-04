package com.nyinyihtunlwin.projects.foodaholic.network.responses

import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel

class CategoriesResponse(@SerializedName("categories") var categories: List<CategoryModel> = ArrayList())