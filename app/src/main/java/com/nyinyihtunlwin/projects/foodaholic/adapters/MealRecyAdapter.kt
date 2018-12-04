package com.nyinyihtunlwin.projects.foodaholic.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.delegates.MealDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.viewholders.MealViewHolder
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseRecyclerAdapter
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder

class MealRecyAdapter(context: Context, var mealDelegate: MealDelegate) :
    BaseRecyclerAdapter<MealViewHolder, MealModel>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MealModel> {
        val view = mLayoutInflator.inflate(R.layout.view_item_meal, parent, false)
        return MealViewHolder(view, mealDelegate)
    }
}