package com.nyinyihtunlwin.projects.foodaholic.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.projects.foodaholic.delegates.IngredientDelegate
import com.nyinyihtunlwin.projects.foodaholic.utils.AppConstants
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_ingredient.view.*

class IngredientsViewHolder(
    itemView: View,
    var ingredientDelegate: IngredientDelegate
) : BaseViewHolder<String>(itemView) {
    override fun setData(data: String) {
        mData = data
        if (mData != null && !mData!!.contentEquals("null")) {
            val url = "${AppConstants.IMAGE_URL}${mData.toString()}.png"
            Glide.with(itemView.context).load(url).into(itemView.iv_ingredient)
        }
    }

    override fun onClick(v: View?) {
        ingredientDelegate.onTapIngredient(mData!!)
    }
}