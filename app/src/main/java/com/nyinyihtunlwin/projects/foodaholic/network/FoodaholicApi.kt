package com.nyinyihtunlwin.projects.foodaholic.network

import com.nyinyihtunlwin.projects.foodaholic.network.responses.CategoriesResponse
import com.nyinyihtunlwin.projects.foodaholic.network.responses.LatestMealsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FoodaholicApi {

    @GET("categories.php")
    fun getCategories(): Observable<CategoriesResponse>

    @GET("latest.php")
    fun getLatestMeals(): Observable<LatestMealsResponse>

    @POST("lookup.php")
    fun getMealById(@Query("i") mealId: String): Observable<LatestMealsResponse>
}