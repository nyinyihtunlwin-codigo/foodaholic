package com.nyinyihtunlwin.projects.foodaholic.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel

@Dao
abstract class MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealModel: MealModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeals(vararg meals: MealModel): LongArray

}