package com.nyinyihtunlwin.projects.foodaholic.network.responses

import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel

class LatestMealsResponse(@SerializedName("meals") var meals: List<MealModel> = ArrayList())