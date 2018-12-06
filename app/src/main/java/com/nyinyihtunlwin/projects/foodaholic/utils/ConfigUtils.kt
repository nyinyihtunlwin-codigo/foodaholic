package com.nyinyihtunlwin.projects.foodaholic.utils

import android.content.Context
import android.content.SharedPreferences

class ConfigUtils(context: Context) {

    private var mSharedPreferences: SharedPreferences =
        context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE)

    companion object {
        val KEY_FIRST_TIME_INSTALL = "KEY_FIRST_TIME_INSTALL"

        private var INSTANCE: ConfigUtils? = null

        fun getInstance(): ConfigUtils {
            if (INSTANCE == null) {
                throw RuntimeException("ConfigUtils is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initConfigUtils(context: Context) {
            INSTANCE = ConfigUtils(context)
        }
    }

    fun setFirstTimeInstalled(isFirstTime: Boolean) {
        mSharedPreferences.edit().putBoolean(KEY_FIRST_TIME_INSTALL, isFirstTime).apply()

    }

    fun isFirstTimeInstalled(): Boolean {
        return mSharedPreferences.getBoolean(KEY_FIRST_TIME_INSTALL, true)
    }

}