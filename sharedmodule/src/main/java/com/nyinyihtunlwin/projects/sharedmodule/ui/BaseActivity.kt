package com.nyinyihtunlwin.projects.sharedmodule.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.view.inputmethod.InputMethodManager

open class BaseActivity : android.support.v7.app.AppCompatActivity(), Observer<Error> {
    override fun onChanged(error: Error?) {

    }

    protected fun hideSoftKeyboard(context: Context) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}