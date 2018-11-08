package com.nyinyihtunlwin.projects.foodaholic.network

import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.BaseViewModel
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FoodaholicDataRepository {

    companion object {
        lateinit var mFoodaholicApi: FoodaholicApi

        private var INSTANCE: FoodaholicDataRepository? = null

        fun getInstance(): FoodaholicDataRepository {
            if (INSTANCE == null) {
                INSTANCE = FoodaholicDataRepository()
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

    fun startLoadingCategories(
        responseLD: MutableLiveData<List<CategoryModel>>,
        errorLd: MutableLiveData<String>
    ) {
        val categoryDisposable = BaseViewModel.mFoodaholicApi.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result?.categories != null && result.categories.size > 0) {
                        responseLD.value = result.categories
                    } else {
                        errorLd.value = "No data"
                    }
                },
                { error ->
                    errorLd.value = error.message.toString()
                }
            )
    }

}