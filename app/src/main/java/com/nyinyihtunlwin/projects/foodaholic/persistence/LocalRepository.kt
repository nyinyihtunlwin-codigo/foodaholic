package com.nyinyihtunlwin.projects.foodaholic.persistence

import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.persistence.db.FoodaholicDB

class LocalRepository {
    companion object {
        private var INSTANCE: LocalRepository? = null
        private var mTheDB: FoodaholicDB? = null
        fun getInstance(): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository()
            }
            var i = INSTANCE
            return i!!
        }

        fun initLocalRepository(context: Context) {
            mTheDB = FoodaholicDB.getDatabase(context)
        }
    }

    fun getDB(): FoodaholicDB {
        return mTheDB!!
    }
}