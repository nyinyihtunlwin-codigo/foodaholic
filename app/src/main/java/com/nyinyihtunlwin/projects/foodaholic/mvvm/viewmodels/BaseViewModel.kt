package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.nyinyihtunlwin.projects.foodaholic.network.FoodaholicApi
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.Subscribe
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {

    companion object {
        lateinit var mFoodaholicApi: FoodaholicApi
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


        mFoodaholicApi = retrofit.create(FoodaholicApi::class.java)

    }

    abstract fun onStart()

    @Subscribe
    fun onEvent(event : Any?) {

    }

}