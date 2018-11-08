package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.LatestView
import java.lang.ref.WeakReference

class LatestViewModelFactory(var contextWeakReference: WeakReference<Context>, var mView: LatestView) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LatestViewModel(contextWeakReference, mView) as T
    }
}