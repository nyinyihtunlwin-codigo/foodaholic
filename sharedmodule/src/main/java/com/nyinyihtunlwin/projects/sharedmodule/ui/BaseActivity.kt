package com.nyinyihtunlwin.projects.sharedmodule.ui

import android.arch.lifecycle.Observer

open class BaseActivity : android.support.v7.app.AppCompatActivity(), Observer<Error> {
    override fun onChanged(error: Error?) {

    }
}