package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.nyinyihtunlwin.projects.foodaholic.activities.MealDetailsActivity
import com.nyinyihtunlwin.projects.foodaholic.adapters.LatestRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.data.LocalRepository
import com.nyinyihtunlwin.projects.foodaholic.delegates.MealDelegate
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
import com.nyinyihtunlwin.projects.foodaholic.utils.AppUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.ref.WeakReference

class LatestViewModel(
    private var contextWeakReference: WeakReference<Context>
) : BaseViewModel(), MealDelegate {

    var mResponseLD: MutableLiveData<List<MealModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: LatestRecyAdapter
    var isLoading = ObservableBoolean()

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        mAdapter = LatestRecyAdapter(contextWeakReference.get()!!, this)

        val value = LocalRepository.getInstance().getDB().mealDao().getLatestMeals()
        if (!value.isEmpty()) {
            mResponseLD.value = value
        } else {
            startLoadingLatestMeals()
        }
    }

    private fun startLoadingLatestMeals() {
        isLoading.set(true)
        if (AppUtils.getInstance().hasConnection()) {
            NetworkRepository.getInstance().startLoadingLatestMeals()
        } else {
            mErrorLD.value = "No internet connection!"
        }
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(contextWeakReference.get(), 2)
    }

    fun getAdapter(): LatestRecyAdapter {
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun onRefresh() {
        startLoadingLatestMeals()
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
                mealModel.idMeal.toString()
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
    fun onMealsLoaded(events: DataEvents.MealsLoadedEvent) {
        mResponseLD.value = events.loadedMeals
        val insertMeals =
            LocalRepository.getInstance().getDB().mealDao().insertMeals(events.loadedMeals)
        Log.e("inserted", insertMeals.size.toString())
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }

}