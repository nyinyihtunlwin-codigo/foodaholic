package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.nyinyihtunlwin.projects.foodaholic.adapters.IngredientsAndMeasurementsRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.adapters.IngredientsRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.data.LocalRepository
import com.nyinyihtunlwin.projects.foodaholic.delegates.IngredientDelegate
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
import com.nyinyihtunlwin.projects.foodaholic.utils.AppUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.ref.WeakReference

class MealDetailsViewModel(
    var contextWeakReference: WeakReference<Context>,
    var mealId: String
) : BaseViewModel(), IngredientDelegate {


    var mResponseLD: MutableLiveData<List<MealModel>> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    private lateinit var mAdapter: IngredientsRecyAdapter
    private lateinit var mIMdapter: IngredientsAndMeasurementsRecyAdapter

    var isLoading = ObservableBoolean()
    var mMealName = ObservableField<String>()
    var mInstruction = ObservableField<String>()
    var mImage = ObservableField<String>()
    var mArea = ObservableField<String>()

    var mYoutubeUrl: String? = null

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        mAdapter = IngredientsRecyAdapter(contextWeakReference.get()!!, this)
        mIMdapter = IngredientsAndMeasurementsRecyAdapter(contextWeakReference.get()!!)

        val value = LocalRepository.getInstance().getDB().mealDao().getMealById(mealId)
        if (value == null) {
            startLoadingMealById(mealId)
        } else {
            mResponseLD.value = arrayListOf(value)
        }
    }

    private fun startLoadingMealById(mealId: String) {
        isLoading.set(true)
        if (AppUtils.getInstance().hasConnection()) {
            NetworkRepository.getInstance().startLoadingMealById(mealId)
        } else {
            mErrorLD.value = "No internet connection!"
        }
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(contextWeakReference.get()!!, LinearLayoutManager.HORIZONTAL, false)
    }

    fun getGridLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(contextWeakReference.get()!!, 2)
    }

    fun getAdapter(): IngredientsRecyAdapter {
        return mAdapter
    }

    fun getGridAdapter(): IngredientsAndMeasurementsRecyAdapter {
        return mIMdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun onRefresh() {
        startLoadingMealById(mealId)
    }

    fun onTapPlay(view: View) {
        val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mYoutubeUrl))
        contextWeakReference.get()!!.startActivity(youtubeIntent)
    }

    override fun onCleared() {
        super.onCleared()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        contextWeakReference.clear()
    }

    override fun onTapIngredient(ingredient: String) {

    }

    fun setNewData(meals: List<MealModel>) {
        val mealModel = meals[0]
        val ingredients = mealModel.getIngredients()
        val ingredientsAndMeasurements = mealModel.getIngredientsAndMeasurement()

        mAdapter.setNewData(ingredients as MutableList<String>)
        mIMdapter.setNewData(ingredientsAndMeasurements as MutableList<String>)

        mMealName.set(mealModel.strMeal)
        mInstruction.set(mealModel.strInstructions)
        mImage.set(mealModel.strMealThumb)
        mArea.set(mealModel.strArea)
        mYoutubeUrl = mealModel.strYoutube
    }

    fun dismissLoading() {
        isLoading.set(false)
    }

    @Subscribe
    fun onMealLoaded(events: DataEvents.MealDetailsLoadedEvent) {
        mResponseLD.value = events.loadedMeals
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }
}