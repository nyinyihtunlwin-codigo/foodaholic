package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import java.lang.ref.WeakReference

class MealDetailsViewModelFactory(var contextWeakReference: WeakReference<Context>,
                                  var mealId:String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealDetailsViewModel(contextWeakReference,mealId) as T
    }
}