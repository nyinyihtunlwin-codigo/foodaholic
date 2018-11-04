package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nyinyihtunlwin.projects.foodaholic.adapters.CategoryRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.CategoryView
import java.lang.ref.WeakReference

class CategoryViewModel(
    var contextWeakReference: WeakReference<Context>,
    var mView: CategoryView
) : ViewModel() {

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(contextWeakReference.get())
    }

    fun getAdapter(): CategoryRecyAdapter {
        return CategoryRecyAdapter(contextWeakReference.get()!!)
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

}