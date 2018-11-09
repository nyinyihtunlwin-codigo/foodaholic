package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.CategoryView
import java.lang.ref.WeakReference

class CategoryViewModelFactory(var contextWeakReference: WeakReference<Context>) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(contextWeakReference) as T
    }
}