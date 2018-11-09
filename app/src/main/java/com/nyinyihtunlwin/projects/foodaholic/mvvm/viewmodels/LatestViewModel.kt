package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.content.Context
import android.databinding.ObservableBoolean
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.nyinyihtunlwin.projects.foodaholic.activities.MealDetailsActivity
import com.nyinyihtunlwin.projects.foodaholic.adapters.LatestRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.delegates.MealDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.LatestView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class LatestViewModel(
    private var contextWeakReference: WeakReference<Context>,
    private var mView: LatestView
) : BaseViewModel(), MealDelegate {

    private var mCompositeDisposable = CompositeDisposable()
    private lateinit var mAdapter: LatestRecyAdapter
    var isLoading = ObservableBoolean()

    fun startLoadingLatestMeals() {
        isLoading.set(true)
        val latestDisposable = mFoodaholicApi.getLatestMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result?.meals != null && result.meals.size > 0) {
                        mView.onDataLoaded(result.meals)
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
        mCompositeDisposable.add(latestDisposable)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(contextWeakReference.get(), 2)
    }

    fun getAdapter(): LatestRecyAdapter {
        mAdapter = LatestRecyAdapter(contextWeakReference.get()!!, this)
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun getOnScrolled(): CenterScrollListener {
        return CenterScrollListener()
    }

    fun onRefresh() {
        startLoadingLatestMeals()
    }

    override fun onCleared() {
        super.onCleared()
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        contextWeakReference.clear()
    }

    override fun onTapMeal(mealModel: MealModel) {
        contextWeakReference.get()!!.startActivity(
            MealDetailsActivity.newInstnace(
                contextWeakReference.get()!!.applicationContext,
                ""
            )
        )
    }

    fun setNewData(mealList: List<MealModel>) {
        mAdapter.setNewData(mealList as MutableList<MealModel>)
    }

}