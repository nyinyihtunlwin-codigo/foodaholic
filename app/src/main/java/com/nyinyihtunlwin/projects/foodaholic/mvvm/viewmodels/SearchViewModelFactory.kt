package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import java.lang.ref.WeakReference

class SearchViewModelFactory(var contextWeakReference: WeakReference<Context>) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(contextWeakReference) as T
    }
}