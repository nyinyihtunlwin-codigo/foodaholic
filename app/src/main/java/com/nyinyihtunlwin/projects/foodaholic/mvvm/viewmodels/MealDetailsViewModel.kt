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
import android.util.Log
import android.view.View
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.adapters.IngredientsAndMeasurementsRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.adapters.IngredientsRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.delegates.IngredientDelegate
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.BookmarkModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
import com.nyinyihtunlwin.projects.foodaholic.persistence.LocalRepository
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
    var isVisibleBookmarkIcon = ObservableBoolean()
    var isBookmarked = ObservableBoolean()
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
        val valueBookmark = LocalRepository.getInstance().getDB().bookmarkDao().getMealById(mealId)
        if (value == null) {
            startLoadingMealById(mealId)
        } else {
            isVisibleBookmarkIcon.set(true)
            mResponseLD.value = arrayListOf(value)
        }
        if (valueBookmark != null) {
            isBookmarked.set(true)
        } else {
            isBookmarked.set(false)
        }
    }

    private fun startLoadingMealById(mealId: String) {
        isLoading.set(true)
        isVisibleBookmarkIcon.set(false)
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
        if (mYoutubeUrl != null && mYoutubeUrl != "") {
            val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mYoutubeUrl))
            contextWeakReference.get()!!.startActivity(youtubeIntent)
        } else {
            Toast.makeText(
                contextWeakReference.get()!!.applicationContext,
                "No video found!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onTapBookmark(view: View) {
        val mealModel = mResponseLD.value!![0]
        val bookmarkModel = BookmarkModel().apply {
            idMeal = mealModel.idMeal
            strMeal = mealModel.strMeal
            strMealThumb = mealModel.strMealThumb
        }

        if (LocalRepository.getInstance().getDB().bookmarkDao().getMealById(bookmarkModel.idMeal) != null) {
            val deleteFromBookmark =
                LocalRepository.getInstance().getDB().bookmarkDao().deleteMealById(bookmarkModel.idMeal)
            Log.e("deleted from bookmark", deleteFromBookmark.toString())

            val deleteMealById = LocalRepository.getInstance().getDB().mealDao().deleteMealById(mealModel.idMeal)
            isBookmarked.set(false)
        } else {
            val insertBookmarkedMeal =
                LocalRepository.getInstance().getDB().bookmarkDao().insertMeal(bookmarkModel)
            Log.e("inserted into bookmark", insertBookmarkedMeal.toString())

            val meal = LocalRepository.getInstance().getDB().mealDao().getMealById(mealModel.idMeal)
            if (meal == null) {
                LocalRepository.getInstance().getDB().mealDao().insertMeal(mealModel.apply { isLatest = false })
            }
            isBookmarked.set(true)
        }

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
        isVisibleBookmarkIcon.set(true)
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }
}