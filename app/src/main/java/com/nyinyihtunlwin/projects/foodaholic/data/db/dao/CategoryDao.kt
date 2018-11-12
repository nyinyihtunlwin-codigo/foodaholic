package com.nyinyihtunlwin.projects.foodaholic.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants


@Dao
abstract interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategory(categoryModel: CategoryModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategories(categories: List<CategoryModel>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_CATEGORIES}")
    abstract fun getCategories(): List<CategoryModel>

}