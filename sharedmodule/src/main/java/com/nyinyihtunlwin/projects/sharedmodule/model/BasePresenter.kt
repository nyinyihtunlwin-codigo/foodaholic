package com.nyinyihtunlwin.projects.sharedmodule.model

import android.arch.lifecycle.MutableLiveData


open class BasePresenter<T : BaseView> {

    protected lateinit var mView: T
    private lateinit var mErrorLD: MutableLiveData<Error>

    val errorLD: MutableLiveData<Error>
        get() = mErrorLD

    open fun onCreate(view: T) {
        this.mView = view
        mErrorLD = MutableLiveData()
    }

}