package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityMainBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModelFactory
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.CategoryView
import com.nyinyihtunlwin.projects.foodaholic.network.FoodaholicRepository
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : BaseActivity(), CategoryView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        )
        setSupportActionBar(toolbar)
        contentView.viewModel = ViewModelProviders.of(
            this@MainActivity,
            CategoryViewModelFactory(WeakReference(this), this)
        ).get(CategoryViewModel::class.java)

        FoodaholicRepository.getInstance()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDataLoaded(catList: List<CategoryModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
