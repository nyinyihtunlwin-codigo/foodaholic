package com.nyinyihtunlwin.projects.foodaholic.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.delegates.BookmarkDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel
import com.nyinyihtunlwin.projects.foodaholic.viewholders.BookmarkViewHolder
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseRecyclerAdapter
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder

class BookmarkRecyAdapter(context: Context, var bookmarkDelegate: BookmarkDelegate) :
    BaseRecyclerAdapter<BookmarkViewHolder, BookmarkModel>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BookmarkModel> {
        val view = mLayoutInflator.inflate(R.layout.view_item_meal, parent, false)
        return BookmarkViewHolder(view, bookmarkDelegate)
    }
}