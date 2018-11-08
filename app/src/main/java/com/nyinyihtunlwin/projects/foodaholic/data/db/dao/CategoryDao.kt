package com.nyinyihtunlwin.projects.foodaholic.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel


@Dao
abstract class CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategory(categoryModel: CategoryModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategories(vararg categories: CategoryModel): LongArray

}