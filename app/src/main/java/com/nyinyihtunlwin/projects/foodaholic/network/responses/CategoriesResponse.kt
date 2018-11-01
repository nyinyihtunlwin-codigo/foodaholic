package com.nyinyihtunlwin.projects.foodaholic.network.responses

import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.projects.foodaholic.data.vos.CategoryVO

class CategoriesResponse(@SerializedName("categories") var categories: List<CategoryVO> = ArrayList())