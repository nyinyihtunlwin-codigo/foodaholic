package com.nyinyihtunlwin.projects.foodaholic.mvvm.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_BOOKMARKS)
class BookmarkModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("idMeal") var idMeal: String = "",
    @SerializedName("strMeal") var strMeal: String? = null,
    @SerializedName("strMealThumb") var strMealThumb: String? = null
)
