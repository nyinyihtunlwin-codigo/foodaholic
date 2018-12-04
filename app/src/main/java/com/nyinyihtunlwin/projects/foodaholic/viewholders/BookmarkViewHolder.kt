package com.nyinyihtunlwin.projects.foodaholic.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.projects.foodaholic.delegates.BookmarkDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_category.view.*

class BookmarkViewHolder(
    itemView: View,
    var bookmarkDelegate: BookmarkDelegate
) : BaseViewHolder<BookmarkModel>(itemView) {

    override fun setData(data: BookmarkModel) {
        mData = data
        if (mData != null) {
            itemView.tv_title.text = mData!!.strMeal
            Glide.with(itemView.iv_category.context)
                .load(mData!!.strMealThumb)
                .into(itemView.iv_category)
        }
    }

    override fun onClick(v: View?) {
        bookmarkDelegate.onTapMeal(mData!!)
    }
}