package com.nyinyihtunlwin.projects.foodaholic.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.projects.foodaholic.delegates.MealDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_category.view.*

class MealViewHolder(
    itemView: View,
    var mealDelegate: MealDelegate
) : BaseViewHolder<MealModel>(itemView) {

    override fun setData(data: MealModel) {
        mData = data
        if (mData != null) {
            itemView.tv_title.text = mData!!.strMeal
            Glide.with(itemView.iv_category.context)
                .load(mData!!.strMealThumb)
                .into(itemView.iv_category)
        }
    }

    override fun onClick(v: View?) {
        mealDelegate.onTapMeal(mData!!)
    }
}