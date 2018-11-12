package com.nyinyihtunlwin.projects.foodaholic.mvvm.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_CATEGORIES)
data class CategoryModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @SerializedName("idCategory")  @ColumnInfo(name = "idCategory") var idCategory: String = "",
    @SerializedName("strCategory") @ColumnInfo(name = "strCategory") var strCategory: String = "",
    @SerializedName("strCategoryThumb") @ColumnInfo(name = "strCategoryThumb") var strCategoryThumb: String = "",
    @SerializedName("strCategoryDescription") @ColumnInfo(name = "strCategoryDescription") var strCategoryDescription: String = ""
) : BaseObservable()