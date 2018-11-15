package com.nyinyihtunlwin.projects.foodaholic.mvvm

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view).load(url).into(view)
}
