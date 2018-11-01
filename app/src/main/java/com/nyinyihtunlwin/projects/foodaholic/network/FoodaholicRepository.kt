package com.nyinyihtunlwin.projects.foodaholic.network

import com.google.gson.Gson
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FoodaholicRepository {

    companion object {
        lateinit var mFoodaholicApi: FoodaholicApi

        private var INSTANCE: FoodaholicRepository? = null

        fun getInstance(): FoodaholicRepository {
            if (INSTANCE == null) {
                INSTANCE = FoodaholicRepository()
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

}