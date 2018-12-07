package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import java.lang.ref.WeakReference


class InfoViewModel(
    private var contextWeakReference: WeakReference<Context>
) : BaseViewModel() {

    override fun onStart() {

    }

    fun onTapFacebook(view: View) {
        if (isAppInstalled(contextWeakReference.get()!!.applicationContext, "com.facebook.katana")) {
            open("fb://profile/100002954864451")
        } else {
            open("https://facebook.com/nyinyi.htunlwin")
        }
    }

    fun onTapLinkedIn(view: View) {
        open("https://www.linkedin.com/in/nyinyi-htunlwin-1b2999112/")
    }

    fun onTapGitHub(view: View) {
        open("https://github.com/nyinyihtunlwin")
    }

    private fun isAppInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

    }

    private fun open(url: String) {
        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (contextWeakReference.get() != null) {
            contextWeakReference.get()!!.startActivity(viewIntent)
        }
    }
}