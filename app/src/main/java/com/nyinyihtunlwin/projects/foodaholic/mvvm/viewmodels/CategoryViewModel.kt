package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.nyinyihtunlwin.projects.foodaholic.adapters.CategoryRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
import java.lang.ref.WeakReference

class CategoryViewModel(
    private var contextWeakReference: WeakReference<Context>
) : BaseViewModel() {

    var mResponseLD: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: CategoryRecyAdapter
    var isLoading = ObservableBoolean()

    fun startLoadingCategories() {
        isLoading.set(true)
        NetworkRepository.getInstance().startLoadingCategories(mResponseLD, mErrorLD)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(contextWeakReference.get())
    }

    fun getAdapter(): CategoryRecyAdapter {
        mAdapter = CategoryRecyAdapter(contextWeakReference.get()!!)
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun getOnScrolled(): CenterScrollListener {
        return CenterScrollListener()
    }

    fun onRefresh() {
        startLoadingCategories()
    }

    override fun onCleared() {
        super.onCleared()
        contextWeakReference.clear()
    }

    fun setNewData(catList: List<CategoryModel>) {
        mAdapter.setNewData(catList as MutableList<CategoryModel>)
    }

    fun dismissLoading() {
        isLoading.set(false)
    }

}