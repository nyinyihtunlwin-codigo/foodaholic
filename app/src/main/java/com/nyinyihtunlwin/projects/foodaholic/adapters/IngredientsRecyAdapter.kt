package com.nyinyihtunlwin.projects.foodaholic.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.viewholders.IngredientsViewHolder
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseRecyclerAdapter
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder

class IngredientsRecyAdapter(context: Context) : BaseRecyclerAdapter<IngredientsViewHolder, String>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return IngredientsViewHolder(mLayoutInflator.inflate(R.layout.view_item_ingredient, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}