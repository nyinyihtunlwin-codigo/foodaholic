package com.nyinyihtunlwin.projects.foodaholic.network

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.BaseViewModel
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkRepository {

    companion object {
        lateinit var mFoodaholicApi: FoodaholicApi

        private var INSTANCE: NetworkRepository? = null

        fun getInstance(): NetworkRepository {
            if (INSTANCE == null) {
                INSTANCE = NetworkRepository()
            }
            return INSTANCE!!
        }
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        mFoodaholicApi = retrofit!!.create(FoodaholicApi::class.java)
    }

    @SuppressLint("CheckResult")
    fun startLoadingCategories() {
        BaseViewModel.mFoodaholicApi.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null
                        && result.categories.isNotEmpty()
                    ) {
                        val categoriesLoadedEvent = DataEvents.CategoriesLoadedEvent(result.categories)
                        EventBus.getDefault().post(categoriesLoadedEvent)
                    } else {
                        if (result != null)
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent("No data found!"))
                        else
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent())
                    }
                },
                { error ->
                    EventBus.getDefault().post(ErrorEvents.ApiErrorEvent(error))
                }
            )
    }

    @SuppressLint("CheckResult")
    fun startLoadingLatestMeals() {
        BaseViewModel.mFoodaholicApi.getLatestMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null
                        && result.meals.isNotEmpty()
                    ) {
                        val mealsLoadedEvent = DataEvents.LatestMealsLoadedEvent(result.meals)
                        EventBus.getDefault().post(mealsLoadedEvent)
                    } else {
                        if (result != null)
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent("No data found!"))
                        else
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent())
                    }
                },
                { error ->
                    EventBus.getDefault().post(ErrorEvents.ApiErrorEvent(error))
                }
            )
    }

    @SuppressLint("CheckResult")
    fun startLoadingMealById(mealId: String) {
        BaseViewModel.mFoodaholicApi.getMealById(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null
                        && result.meals.isNotEmpty()
                    ) {
                        val mealsLoadedEvent = DataEvents.MealDetailsLoadedEvent(result.meals)
                        EventBus.getDefault().post(mealsLoadedEvent)
                    } else {
                        if (result != null)
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent("No data found!"))
                        else
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent())
                    }
                },
                { error ->
                    EventBus.getDefault().post(ErrorEvents.ApiErrorEvent(error))
                }
            )
    }

    @SuppressLint("CheckResult")
    fun startLoadingMealsByCategory(categoryName: String) {
        BaseViewModel.mFoodaholicApi.getMealsByCategory(categoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null
                        && result.meals.isNotEmpty()
                    ) {
                        val mealsLoadedEvent = DataEvents.CategoryMealsLoadedEvent(result.meals)
                        EventBus.getDefault().post(mealsLoadedEvent)
                    } else {
                        if (result != null)
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent("No data found!"))
                        else
                            EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent())
                    }
                },
                { error ->
                    EventBus.getDefault().post(ErrorEvents.ApiErrorEvent(error))
                }
            )
    }

    @SuppressLint("CheckResult")
    fun startSearching(keywords: String) {
        BaseViewModel.mFoodaholicApi.searchMeals(keywords)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result?.meals != null
                    ) {
                        val mealsLoadedEvent = DataEvents.MealSearchedLoadedEvent(result.meals)
                        EventBus.getDefault().post(mealsLoadedEvent)
                    } else {
                        EventBus.getDefault().post(DataEvents.EmptyDataLoadedEvent("No data found!"))
                    }
                },
                { error ->
                    EventBus.getDefault().post(ErrorEvents.ApiErrorEvent(error))
                }
            )
    }
}