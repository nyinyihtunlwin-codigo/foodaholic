package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import java.lang.ref.WeakReference

class CategoryDetailsViewModelFactory(
    var contextWeakReference: WeakReference<Context>,
    var categoryName: String,
    var categoryImage: String,
    var categoryDescrption: String
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryDetailsViewModel(contextWeakReference,
            categoryName,
            categoryImage,
            categoryDescrption) as T
    }
}