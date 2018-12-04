package com.nyinyihtunlwin.projects.foodaholic.persistence.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants

@Dao
abstract interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealModel: BookmarkModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeals(meals: List<BookmarkModel>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_BOOKMARKS}")
    abstract fun getBookmarkedMeals(): List<BookmarkModel>

    @Query("SELECT * FROM ${AppConstants.TABLE_BOOKMARKS} WHERE idMeal = :mealId")
    abstract fun getMealById(mealId: String): BookmarkModel?

    @Query("DELETE FROM ${AppConstants.TABLE_BOOKMARKS} WHERE idMeal = :mealId")
    abstract fun deleteMealById(mealId: String): Int

}