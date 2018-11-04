package com.nyinyihtunlwin.projects.foodaholic.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.viewholders.CategoryViewHolder
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseRecyclerAdapter
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder

class CategoryRecyAdapter(context: Context) : BaseRecyclerAdapter<CategoryViewHolder, CategoryModel>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CategoryModel> {
        var view = mLayoutInflator.inflate(R.layout.view_item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<CategoryModel>, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}