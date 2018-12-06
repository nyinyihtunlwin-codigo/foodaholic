package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.nyinyihtunlwin.projects.foodaholic.activities.MealDetailsActivity
import com.nyinyihtunlwin.projects.foodaholic.adapters.BookmarkRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.delegates.BookmarkDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel
import com.nyinyihtunlwin.projects.foodaholic.persistence.LocalRepository
import org.greenrobot.eventbus.EventBus
import java.lang.ref.WeakReference

class BookmarkViewModel(
    private var contextWeakReference: WeakReference<Context>
) : BaseViewModel(), BookmarkDelegate {

    var mResponseLD: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: BookmarkRecyAdapter

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        mAdapter = BookmarkRecyAdapter(contextWeakReference.get()!!, this)
        val value = LocalRepository.getInstance().getDB().bookmarkDao().getBookmarkedMeals()
        if (!value.isEmpty()) {
            mResponseLD.value = value
        } else {
            mErrorLD.value = "No data."
        }
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(contextWeakReference.get(), 2)
    }

    fun getAdapter(): BookmarkRecyAdapter {
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    override fun onCleared() {
        super.onCleared()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        mResponseLD = MutableLiveData()
        mErrorLD = MutableLiveData()
        contextWeakReference.clear()
    }

    override fun onTapMeal(bookmarkModel: BookmarkModel) {
        contextWeakReference.get()!!.startActivity(
            MealDetailsActivity.newInstnace(
                contextWeakReference.get()!!.applicationContext,
                bookmarkModel.idMeal
            )
        )
    }

    fun setNewData(mealList: List<BookmarkModel>) {
        mAdapter.setNewData(mealList as MutableList<BookmarkModel>)
    }

}