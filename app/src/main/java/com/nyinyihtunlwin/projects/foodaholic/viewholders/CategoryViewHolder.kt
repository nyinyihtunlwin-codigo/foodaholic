package com.nyinyihtunlwin.projects.foodaholic.viewholders

import android.view.View
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_category.view.*

class CategoryViewHolder(itemView: View) : BaseViewHolder<CategoryModel>(itemView) {

    override fun setData(data: CategoryModel) {
        mData = data
        if (mData != null) {
            itemView.tv_title.text = mData!!.strCategory
        }
    }

    override fun onClick(v: View?) {
    }
}