package com.nyinyihtunlwin.projects.sharedmodule.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class BasePresenter<T : BaseView> : ViewModel() {

    protected lateinit var mView: T
    private lateinit var mErrorLD: MutableLiveData<Error>

    val errorLD: MutableLiveData<Error>
        get() = mErrorLD

    fun onCreate(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }

}