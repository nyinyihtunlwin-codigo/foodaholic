package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.content.Context
import android.view.View
import com.nyinyihtunlwin.projects.foodaholic.activities.SearchActivity
import java.lang.ref.WeakReference

class MainViewModel(var contextWeakReference: WeakReference<Context>) : BaseViewModel() {
    override fun onStart() {

    }

    fun onTapSearch(view: View) {
        contextWeakReference.get()!!.startActivity(SearchActivity.newInstnace(contextWeakReference.get()!!))
    }

    fun onTapInfo(view: View) {

    }
}