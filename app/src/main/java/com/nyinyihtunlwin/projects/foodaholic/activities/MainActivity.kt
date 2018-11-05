package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityMainBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModelFactory
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.CategoryView
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : BaseActivity(), CategoryView {

    lateinit var contentView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )

        setSupportActionBar(toolbar)

        contentView.viewModel = ViewModelProviders.of(
            this@MainActivity,
            CategoryViewModelFactory(WeakReference(this), this)
        ).get(CategoryViewModel::class.java)

        contentView.viewModel!!.startLoadingCategories()

    }

    override fun onDataLoaded(catList: List<CategoryModel>) {
        contentView.viewModel!!.setNewData(catList)
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
