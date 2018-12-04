package com.nyinyihtunlwin.projects.foodaholic.persistence.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.persistence.db.dao.BookmarkDao
import com.nyinyihtunlwin.projects.foodaholic.persistence.db.dao.CategoryDao
import com.nyinyihtunlwin.projects.foodaholic.persistence.db.dao.MealDao


@Database(
    entities = [CategoryModel::class, MealModel::class, BookmarkModel::class], version = 1, exportSchema = false
)
abstract class FoodaholicDB : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao
    abstract fun bookmarkDao(): BookmarkDao


    companion object {

        private const val DB_NAME = "Foodaholic.db"
        private var INSTANCE: FoodaholicDB? = null

        fun getDatabase(context: Context): FoodaholicDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FoodaholicDB::class.java, DB_NAME)
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build()
            }
            return INSTANCE!!
        }
    }
}