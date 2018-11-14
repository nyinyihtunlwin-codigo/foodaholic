package com.nyinyihtunlwin.projects.foodaholic.viewholders

import android.view.View
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_ingredient_measurement.view.*

class IngredientsAndMeasurementViewHolder(
    itemView: View
) : BaseViewHolder<String>(itemView) {
    override fun setData(data: String) {
        mData = data
        if (mData != null && !mData!!.contentEquals("null")) {
            itemView.tv_ingredient_measurement.text = mData.toString()
        }
    }

    override fun onClick(v: View?) {
    }
}