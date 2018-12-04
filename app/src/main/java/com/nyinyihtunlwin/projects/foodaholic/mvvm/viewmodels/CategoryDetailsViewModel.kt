package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
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

class CategoryDetailsViewModel(
    private var contextWeakReference: WeakReference<Context>,
    var categoryName: String,
    var categoryImage: String,
    var categoryDescription: String
) : BaseViewModel(), MealDelegate {

    var mResponseLD: MutableLiveData<List<MealModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: MealRecyAdapter

    var isLoading = ObservableBoolean()
    var mCategoryName = ObservableField<String>()
    var mDescription = ObservableField<String>()
    var mImage = ObservableField<String>()
    var mArea = ObservableField<String>()

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        mAdapter = MealRecyAdapter(contextWeakReference.get()!!, this)
        startLoadingMealsByCategory(categoryName)
    }

    private fun startLoadingMealsByCategory(categoryName: String) {
        isLoading.set(true)
        if (AppUtils.getInstance().hasConnection()) {
            NetworkRepository.getInstance().startLoadingMealsByCategory(categoryName)
        } else {
            mErrorLD.value = "No internet connection!"
        }
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(contextWeakReference.get()!!, 2)
    }

    fun getAdapter(): MealRecyAdapter {
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun onRefresh() {
        startLoadingMealsByCategory(categoryName)
    }

    override fun onTapMeal(mealModel: MealModel) {
        contextWeakReference.get()!!.startActivity(
            MealDetailsActivity.newInstnace(
                contextWeakReference.get()!!.applicationContext,
                mealModel.idMeal
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        contextWeakReference.clear()
    }

    fun setNewData(meals: List<MealModel>) {
        mAdapter.setNewData(meals as MutableList<MealModel>)
        mCategoryName.set(categoryName)
        mImage.set(categoryImage)
        mDescription.set(categoryDescription)
    }

    fun dismissLoading() {
        isLoading.set(false)
    }

    @Subscribe
    fun onMealLoaded(events: DataEvents.CategoryMealsLoadedEvent) {
        mResponseLD.value = events.loadedMeals
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }
}