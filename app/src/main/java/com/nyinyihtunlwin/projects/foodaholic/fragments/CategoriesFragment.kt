package com.nyinyihtunlwin.projects.foodaholic.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.FragmentCategoriesBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseFragment
import java.lang.ref.WeakReference


class CategoriesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView =
            DataBindingUtil.inflate<FragmentCategoriesBinding>(inflater, R.layout.fragment_categories, container, false)
        inflatedView.viewModel = ViewModelProviders.of(
            this@CategoriesFragment,
            CategoryViewModelFactory(WeakReference(activity!!.applicationContext))
        ).get(CategoryViewModel::class.java)

        inflatedView.viewModel!!.startLoadingCategories()
        inflatedView.viewModel!!.mResponseLD.observe(this, Observer {
            inflatedView.viewModel!!.setNewData(it as MutableList)
            inflatedView.viewModel!!.dismissLoading()
        })
        inflatedView.viewModel!!.mErrorLD.observe(this, Observer {
            inflatedView.viewModel!!.dismissLoading()
            Toast.makeText(activity!!.applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })
        return inflatedView.root
    }

}
