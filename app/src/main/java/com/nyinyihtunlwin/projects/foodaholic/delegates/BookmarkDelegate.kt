package com.nyinyihtunlwin.projects.foodaholic.delegates

import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel

interface BookmarkDelegate {
    fun onTapMeal(bookmarkModel: BookmarkModel)
}