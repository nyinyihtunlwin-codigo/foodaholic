package com.nyinyihtunlwin.projects.foodaholic.network

import com.nyinyihtunlwin.projects.foodaholic.network.responses.CategoriesResponse
import com.nyinyihtunlwin.projects.foodaholic.network.responses.LatestMealsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface FoodaholicApi {

    @GET("categories.php")
    fun getCategories(): Observable<CategoriesResponse>

    @GET("latest.php")
    fun getLatestMeals(): Observable<LatestMealsResponse>
}