package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.nyinyihtunlwin.projects.foodaholic.adapters.CategoryRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.CategoryView
import com.nyinyihtunlwin.projects.foodaholic.network.FoodaholicDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class CategoryViewModel(
    private var contextWeakReference: WeakReference<Context>,
    private var mView: CategoryView
) : BaseViewModel() {

    var mResponseLD: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private var mCompositeDisposable = CompositeDisposable()
    private lateinit var mAdapter: CategoryRecyAdapter
    var isLoading = ObservableBoolean()

    fun startLoadingCategories() {
        isLoading.set(true)
        FoodaholicDataRepository.getInstance().startLoadingCategories(mResponseLD, mErrorLD)
        val categoryDisposable = mFoodaholicApi.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result?.categories != null && result.categories.size > 0) {
                        mView.onDataLoaded(result.categories)
                    } else {
                        mView.onError("No data")
                    }
                    isLoading.set(false)
                },
                { error ->
                    isLoading.set(false)
                    mView.onError(error.message.toString())
                }
            )
        mCompositeDisposable.add(categoryDisposable)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        /*val carouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
        carouselLayoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())*/
        return LinearLayoutManager(contextWeakReference.get())
    }

    fun getLayoutManagerH(): RecyclerView.LayoutManager {
        /*val carouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
        carouselLayoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())*/
        return LinearLayoutManager(contextWeakReference.get(),LinearLayoutManager.HORIZONTAL,false)
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
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        contextWeakReference.clear()
    }

    fun setNewData(catList: List<CategoryModel>) {
        mAdapter.setNewData(catList as MutableList<CategoryModel>)
    }

}