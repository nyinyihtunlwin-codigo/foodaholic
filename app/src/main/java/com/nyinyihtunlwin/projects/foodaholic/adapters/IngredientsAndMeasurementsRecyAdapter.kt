package com.nyinyihtunlwin.projects.foodaholic.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.viewholders.IngredientsAndMeasurementViewHolder
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseRecyclerAdapter
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder

class IngredientsAndMeasurementsRecyAdapter(context: Context) :
    BaseRecyclerAdapter<IngredientsAndMeasurementViewHolder, String>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return IngredientsAndMeasurementViewHolder(
            mLayoutInflator.inflate(
                R.layout.view_item_ingredient_measurement,
                parent,
                false
            )
        )
    }
}