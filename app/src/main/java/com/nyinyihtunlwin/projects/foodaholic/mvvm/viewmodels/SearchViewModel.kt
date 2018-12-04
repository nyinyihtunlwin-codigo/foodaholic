package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.nyinyihtunlwin.projects.foodaholic.activities.MealDetailsActivity
import com.nyinyihtunlwin.projects.foodaholic.adapters.MealRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.delegates.MealDelegate
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
import com.nyinyihtunlwin.projects.foodaholic.utils.AppUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.ref.WeakReference

class SearchViewModel(var contextWeakReference: WeakReference<Context>) : BaseViewModel(), MealDelegate {

    var mResponseLD: MutableLiveData<List<MealModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: MealRecyAdapter
    var isLoading = ObservableBoolean()

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        mAdapter = MealRecyAdapter(contextWeakReference.get()!!, this)
    }

    fun startSearchingMeals(keywords: String) {
        mAdapter.clearData()
        isLoading.set(true)
        if (AppUtils.getInstance().hasConnection()) {
            NetworkRepository.getInstance().startSearching(keywords)
        } else {
            mErrorLD.value = "No internet connection!"
        }
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(contextWeakReference.get(), 2)
    }

    fun getAdapter(): MealRecyAdapter {
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
        contextWeakReference.clear()
    }

    override fun onTapMeal(mealModel: MealModel) {
        contextWeakReference.get()!!.startActivity(
            MealDetailsActivity.newInstnace(
                contextWeakReference.get()!!.applicationContext,
                mealModel.idMeal
            )
        )
    }

    fun setNewData(mealList: List<MealModel>) {
        mAdapter.setNewData(mealList as MutableList<MealModel>)
    }

    fun dismissLoading() {
        isLoading.set(false)
    }

    @Subscribe
    fun onMealsLoaded(events: DataEvents.MealSearchedLoadedEvent) {
        mResponseLD.value = events.loadedMeals
    }

    @Subscribe
    fun onEmptyDataLoaded(event: DataEvents.EmptyDataLoadedEvent) {
        mErrorLD.value = event.errorMsg
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }
}
