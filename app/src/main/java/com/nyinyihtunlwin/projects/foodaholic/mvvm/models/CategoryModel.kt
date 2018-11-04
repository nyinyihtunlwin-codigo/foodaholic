package com.nyinyihtunlwin.projects.foodaholic.mvvm.models

import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class CategoryModel(
    @SerializedName("idCategory") var idCategory: String = "",
    @SerializedName("strCategory") var strCategory: String = "",
    @SerializedName("strCategoryThumb") var strCategoryThumb: String = "",
    @SerializedName("strCategoryDescription") var strCategoryDescription: String = ""
) : BaseObservable()