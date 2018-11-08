package com.nyinyihtunlwin.projects.foodaholic.mvvm.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "categories")
class CategoryModel(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @SerializedName("idCategory") var idCategory: String = "",
    @SerializedName("strCategory") var strCategory: String = "",
    @SerializedName("strCategoryThumb") var strCategoryThumb: String = "",
    @SerializedName("strCategoryDescription") var strCategoryDescription: String = ""
) : BaseObservable()