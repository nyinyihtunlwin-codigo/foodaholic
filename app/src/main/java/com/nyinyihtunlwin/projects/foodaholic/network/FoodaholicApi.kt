package com.nyinyihtunlwin.projects.foodaholic.network

import retrofit2.http.GET

interface FoodaholicApi {

    @GET("categories.php")
    fun getCategories()

}