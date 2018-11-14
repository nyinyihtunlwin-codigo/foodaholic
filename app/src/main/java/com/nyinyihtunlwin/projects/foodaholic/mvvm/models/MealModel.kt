package com.nyinyihtunlwin.projects.foodaholic.mvvm.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_MEALS)
class MealModel(
    @PrimaryKey
    @SerializedName("idMeal") var idMeal: String = "",
    @SerializedName("strMeal") var strMeal: String? = null,
    @SerializedName("strCategory") var strCategory: String? = null,
    @SerializedName("strArea") var strArea: String? = null,
    @SerializedName("strInstructions") var strInstructions: String? = null,
    @SerializedName("strMealThumb") var strMealThumb: String? = null,
    @SerializedName("strTags") var strTags: String? = null,
    @SerializedName("strYoutube") var strYoutube: String? = null,
    @SerializedName("strIngredient1") var strIngredient1: String? = null,
    @SerializedName("strIngredient2") var strIngredient2: String? = null,
    @SerializedName("strIngredient3") var strIngredient3: String? = null,
    @SerializedName("strIngredient4") var strIngredient4: String? = null,
    @SerializedName("strIngredient5") var strIngredient5: String? = null,
    @SerializedName("strIngredient6") var strIngredient6: String? = null,
    @SerializedName("strIngredient7") var strIngredient7: String? = null,
    @SerializedName("strIngredient8") var strIngredient8: String? = null,
    @SerializedName("strIngredient9") var strIngredient9: String? = null,
    @SerializedName("strIngredient10") var strIngredient10: String? = null,
    @SerializedName("strIngredient11") var strIngredient11: String? = null,
    @SerializedName("strIngredient12") var strIngredient12: String? = null,
    @SerializedName("strIngredient13") var strIngredient13: String? = null,
    @SerializedName("strIngredient14") var strIngredient14: String? = null,
    @SerializedName("strIngredient15") var strIngredient15: String? = null,
    @SerializedName("strIngredient16") var strIngredient16: String? = null,
    @SerializedName("strIngredient17") var strIngredient17: String? = null,
    @SerializedName("strIngredient18") var strIngredient18: String? = null,
    @SerializedName("strIngredient19") var strIngredient19: String? = null,
    @SerializedName("strIngredient20") var strIngredient20: String? = null,
    @SerializedName("strMeasure1") var strMeasure1: String? = null,
    @SerializedName("strMeasure2") var strMeasure2: String? = null,
    @SerializedName("strMeasure3") var strMeasure3: String? = null,
    @SerializedName("strMeasure4") var strMeasure4: String? = null,
    @SerializedName("strMeasure5") var strMeasure5: String? = null,
    @SerializedName("strMeasure6") var strMeasure6: String? = null,
    @SerializedName("strMeasure7") var strMeasure7: String? = null,
    @SerializedName("strMeasure8") var strMeasure8: String? = null,
    @SerializedName("strMeasure9") var strMeasure9: String? = null,
    @SerializedName("strMeasure10") var strMeasure10: String? = null,
    @SerializedName("strMeasure11") var strMeasure11: String? = null,
    @SerializedName("strMeasure12") var strMeasure12: String? = null,
    @SerializedName("strMeasure13") var strMeasure13: String? = null,
    @SerializedName("strMeasure14") var strMeasure14: String? = null,
    @SerializedName("strMeasure15") var strMeasure15: String? = null,
    @SerializedName("strMeasure16") var strMeasure16: String? = null,
    @SerializedName("strMeasure17") var strMeasure17: String? = null,
    @SerializedName("strMeasure18") var strMeasure18: String? = null,
    @SerializedName("strMeasure19") var strMeasure19: String? = null,
    @SerializedName("strMeasure20") var strMeasure20: String? = null,
    @SerializedName("strSource") var strSource: String? = null,
    @SerializedName("dateModified") var dateModified: String? = null
) {

    @Ignore
    private lateinit var ingredients: ArrayList<String>

    @Ignore
    private lateinit var ingredientAndMeasurements: ArrayList<String>

    fun getIngredients(): List<String> {
        ingredients = arrayListOf()
        addIngredient(strIngredient1)
        addIngredient(strIngredient2)
        addIngredient(strIngredient3)
        addIngredient(strIngredient4)
        addIngredient(strIngredient5)
        addIngredient(strIngredient6)
        addIngredient(strIngredient7)
        addIngredient(strIngredient8)
        addIngredient(strIngredient9)
        addIngredient(strIngredient10)
        addIngredient(strIngredient11)
        addIngredient(strIngredient12)
        return ingredients
    }

    private fun addIngredient(strIngredient: String?) {
        if (!strIngredient.equals("") && strIngredient != null) {
            ingredients.add(strIngredient.toString())
        }
    }

    fun getIngredientsAndMeasurement(): List<String> {
        ingredientAndMeasurements = arrayListOf()
        addIngredientAndMeasurement(strIngredient1, strMeasure1)
        addIngredientAndMeasurement(strIngredient2, strMeasure2)
        addIngredientAndMeasurement(strIngredient3, strMeasure3)
        addIngredientAndMeasurement(strIngredient4, strMeasure4)
        addIngredientAndMeasurement(strIngredient5, strMeasure5)
        addIngredientAndMeasurement(strIngredient6, strMeasure6)
        addIngredientAndMeasurement(strIngredient7, strMeasure7)
        addIngredientAndMeasurement(strIngredient8, strMeasure8)
        addIngredientAndMeasurement(strIngredient9, strMeasure9)
        addIngredientAndMeasurement(strIngredient10, strMeasure10)
        addIngredientAndMeasurement(strIngredient11, strMeasure11)
        addIngredientAndMeasurement(strIngredient12, strMeasure12)
        return ingredientAndMeasurements
    }

    private fun addIngredientAndMeasurement(strIngredient: String?, strMeasure: String?) {
        if (!strIngredient.equals("") && strIngredient != null) {
            ingredientAndMeasurements.add(strIngredient.toString())
            ingredientAndMeasurements.add(strMeasure.toString())
        }
    }
}