package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.nyinyihtunlwin.projects.foodaholic.activities.CategoryDetailsActivity
import com.nyinyihtunlwin.projects.foodaholic.adapters.CategoryRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.data.LocalRepository
import com.nyinyihtunlwin.projects.foodaholic.delegates.CategoryDelegate
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
import com.nyinyihtunlwin.projects.foodaholic.utils.AppUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.ref.WeakReference

class CategoryViewModel(
    private var contextWeakReference: WeakReference<Context>
) : BaseViewModel(), CategoryDelegate {

    var mResponseLD: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: CategoryRecyAdapter
    var isLoading = ObservableBoolean()

    override fun onStart() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        mAdapter = CategoryRecyAdapter(contextWeakReference.get()!!, this)

        val value = LocalRepository.getInstance().getDB().categoryDao().getCategories()
        if (!value.isEmpty()) {
            mResponseLD.value = value
        } else {
            startLoadingCategories()
        }
    }

    private fun startLoadingCategories() {
        isLoading.set(true)
        if (AppUtils.getInstance().hasConnection()) {
            NetworkRepository.getInstance().startLoadingCategories()
        } else {
            mErrorLD.value = "No internet connection!"
        }
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(contextWeakReference.get())
    }

    fun getAdapter(): CategoryRecyAdapter {
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun onRefresh() {
        startLoadingCategories()
    }

    override fun onCleared() {
        super.onCleared()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        contextWeakReference.clear()
    }

    override fun onTapCategory(categoryModel: CategoryModel) {
        contextWeakReference.get()!!.startActivity(
            CategoryDetailsActivity.newInstnace(contextWeakReference.get()!!,
                categoryModel.strCategory.toString(),
                categoryModel.strCategoryThumb.toString(),
                categoryModel.strCategoryDescription.toString())
        )
    }

    fun setNewData(catList: List<CategoryModel>) {
        mAdapter.setNewData(catList as MutableList<CategoryModel>)
    }

    fun dismissLoading() {
        isLoading.set(false)
    }

    @Subscribe
    fun onCategoriesLoaded(events: DataEvents.CategoriesLoadedEvent) {
        mResponseLD.value = events.loadedCategories
        val insertCategories =
            LocalRepository.getInstance().getDB().categoryDao().insertCategories(events.loadedCategories)
        Log.e("inserted", insertCategories.size.toString())
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }

}