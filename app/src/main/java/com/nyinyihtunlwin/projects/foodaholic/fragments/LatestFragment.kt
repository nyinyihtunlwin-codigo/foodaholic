package com.nyinyihtunlwin.projects.foodaholic.fragments


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.FragmentLatestBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.LatestViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.LatestViewModelFactory
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.LatestView
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseFragment
import java.lang.ref.WeakReference

class LatestFragment : BaseFragment(), LatestView {

    lateinit var inflatedView: FragmentLatestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView =
                DataBindingUtil.inflate(inflater, R.layout.fragment_latest, container, false)
        inflatedView.viewModel = ViewModelProviders.of(
            this@LatestFragment,
            LatestViewModelFactory(WeakReference(activity!!.applicationContext), this)
        ).get(LatestViewModel::class.java)

        inflatedView.viewModel!!.startLoadingLatestMeals()

        return inflatedView.root
    }

    override fun onDataLoaded(mealList: List<MealModel>) {
        inflatedView.viewModel!!.setNewData(mealList)
    }

    override fun onError(message: String) {
        Toast.makeText(activity!!.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
