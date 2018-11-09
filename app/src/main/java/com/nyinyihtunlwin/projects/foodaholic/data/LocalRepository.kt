package com.nyinyihtunlwin.projects.foodaholic.data

import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.data.db.FoodaholicDB
import com.nyinyihtunlwin.projects.foodaholic.data.db.dao.CategoryDao

class LocalRepository {
    companion object {
        private var INSTANCE: LocalRepository? = null
        private var mTheDB: FoodaholicDB? = null
        fun getInstance(): LocalRepository {
            if (INSTANCE == null) {
                throw RuntimeException("LocalRepository is being invoked before initializing.")
            }

            val i = INSTANCE
            return i!!
        }

        fun initLocalRepository(context: Context) {
            INSTANCE = LocalRepository()
            mTheDB = FoodaholicDB.getDatabase(context)
        }
    }

    var categoryDao: CategoryDao? = null
        get() {
            return mTheDB!!.categoryDao()
        }
}