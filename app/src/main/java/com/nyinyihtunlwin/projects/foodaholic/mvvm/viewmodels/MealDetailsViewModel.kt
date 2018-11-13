package com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nyinyihtunlwin.projects.foodaholic.adapters.IngredientsRecyAdapter
import com.nyinyihtunlwin.projects.foodaholic.data.LocalRepository
import com.nyinyihtunlwin.projects.foodaholic.delegates.IngredientDelegate
import com.nyinyihtunlwin.projects.foodaholic.events.DataEvents
import com.nyinyihtunlwin.projects.foodaholic.events.ErrorEvents
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.network.NetworkRepository
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
    var isLoading = ObservableBoolean()
    var mMealName = ObservableField<String>()
    var mInstruction = ObservableField<String>()
    var mImage = ObservableField<String>()
    var mArea = ObservableField<String>()

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        mAdapter = IngredientsRecyAdapter(contextWeakReference.get()!!, this)

        val value = LocalRepository.getInstance().getDB().mealDao().getMealById(mealId)
        if (value == null) {
            startLoadingMealById(mealId)
        } else {
            mResponseLD.value = arrayListOf(value)
        }
    }

    private fun startLoadingMealById(mealId: String) {
        isLoading.set(true)
        NetworkRepository.getInstance().startLoadingMealById(mealId)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(contextWeakReference.get()!!, LinearLayoutManager.HORIZONTAL, false)
    }

    fun getAdapter(): IngredientsRecyAdapter {
        return mAdapter
    }

    fun isHasFixedSize(): Boolean {
        return true
    }

    fun onRefresh() {
        startLoadingMealById(mealId)
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
        var ingredients = arrayListOf<String>()
        if (!mealModel.strIngredient1.equals("") && mealModel.strIngredient1 != null) {
            ingredients.add(mealModel.strIngredient1.toString())
        }
        if (!mealModel.strIngredient2.equals("") && mealModel.strIngredient2 != null) {
            ingredients.add(mealModel.strIngredient2.toString())
        }
        if (!mealModel.strIngredient3.equals("") && mealModel.strIngredient3 != null) {
            ingredients.add(mealModel.strIngredient3.toString())
        }
        if (!mealModel.strIngredient4.equals("") && mealModel.strIngredient4 != null) {
            ingredients.add(mealModel.strIngredient4.toString())
        }
        if (!mealModel.strIngredient5.equals("") && mealModel.strIngredient5 != null) {
            ingredients.add(mealModel.strIngredient5.toString())
        }
        if (!mealModel.strIngredient6.equals("") && mealModel.strIngredient6 != null) {
            ingredients.add(mealModel.strIngredient6.toString())
        }
        if (!mealModel.strIngredient7.equals("") && mealModel.strIngredient7 != null) {
            ingredients.add(mealModel.strIngredient7.toString())
        }
        if (!mealModel.strIngredient8.equals("") && mealModel.strIngredient8 != null) {
            ingredients.add(mealModel.strIngredient8.toString())
        }
        if (!mealModel.strIngredient9.equals("") && mealModel.strIngredient9 != null) {
            ingredients.add(mealModel.strIngredient9.toString())
        }
        if (!mealModel.strIngredient10.equals("") && mealModel.strIngredient10 != null) {
            ingredients.add(mealModel.strIngredient10.toString())
        }
        if (!mealModel.strIngredient11.equals("") && mealModel.strIngredient11 != null) {
            ingredients.add(mealModel.strIngredient11.toString())
        }
        if (!mealModel.strIngredient12.equals("") && mealModel.strIngredient12 != null) {
            ingredients.add(mealModel.strIngredient12.toString())
        }
        mAdapter.setNewData(ingredients as MutableList<String>)
        mMealName.set(mealModel.strMeal)
        mInstruction.set(mealModel.strInstructions)
        mImage.set(mealModel.strMealThumb)
        mArea.set(mealModel.strArea)
    }

    fun dismissLoading() {
        isLoading.set(false)
    }

    @Subscribe
    fun onMealLoaded(events: DataEvents.MealsLoadedEvent) {
        mResponseLD.value = events.loadedMeals
        // show meal
    }

    @Subscribe
    fun onApiErrorLoaded(event: ErrorEvents.ApiErrorEvent) {
        mErrorLD.value = event.getMsg()
    }
}