package com.nyinyihtunlwin.projects.foodaholic.persistence.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants

@Dao
abstract interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealModel: MealModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeals(meals: List<MealModel>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_MEALS} WHERE isLatest = :isLatest")
    abstract fun getLatestMeals(isLatest: Boolean = true): List<MealModel>

    @Query("SELECT * FROM ${AppConstants.TABLE_MEALS} WHERE idMeal = :mealId")
    abstract fun getMealById(mealId: String): MealModel?

    @Query("DELETE FROM ${AppConstants.TABLE_MEALS} WHERE idMeal = :mealId AND isLatest = :isLatest")
    abstract fun deleteMealById(mealId: String, isLatest: Boolean = false): Int

}