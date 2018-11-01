package com.nyinyihtunlwin.projects.foodaholic.data.vos

import com.google.gson.annotations.SerializedName

class CategoryVO(
    @SerializedName("idCategory") var idCategory: String = "",
    @SerializedName("strCategory") var strCategory: String = "",
    @SerializedName("strCategoryThumb") var strCategoryThumb: String = "",
    @SerializedName("strCategoryDescription") var strCategoryDescription: String = ""
)