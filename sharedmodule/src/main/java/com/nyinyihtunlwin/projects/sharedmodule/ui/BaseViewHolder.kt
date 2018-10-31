package com.nyinyihtunlwin.projects.sharedmodule.ui


import android.view.View
import android.support.v7.widget.RecyclerView

abstract class BaseViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mData: W? = null

    init {

        itemView.setOnClickListener(this)
    }

    abstract fun setData(data: W)

}