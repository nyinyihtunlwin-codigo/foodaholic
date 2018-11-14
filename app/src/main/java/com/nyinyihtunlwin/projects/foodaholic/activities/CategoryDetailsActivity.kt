package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityCategoryDetailsBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryDetailsViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryDetailsViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_meal_details.*
import java.lang.ref.WeakReference

class CategoryDetailsActivity : BaseActivity() {

    companion object {
        private const val KEY_CATEGORY_NAME = "KEY_CATEGORY_NAME"
        private const val KEY_CATEGORY_IMAGE = "KEY_CATEGORY_IMAGE"
        private const val KEY_CATEGORY_DESCRIPTION = "KEY_CATEGORY_DESCRIPTION"
        fun newInstnace(
            context: Context,
            categoryName: String,
            categoryThumb: String,
            description: String
        ): Intent {
            var intentToCategoryDetailsActivity = Intent(context, CategoryDetailsActivity::class.java)
            intentToCategoryDetailsActivity.putExtra(KEY_CATEGORY_NAME, categoryName)
            intentToCategoryDetailsActivity.putExtra(KEY_CATEGORY_IMAGE, categoryThumb)
            intentToCategoryDetailsActivity.putExtra(KEY_CATEGORY_DESCRIPTION, description)
            return intentToCategoryDetailsActivity
        }
    }

    lateinit var mViewModel: CategoryDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView: ActivityCategoryDetailsBinding = DataBindingUtil.setContentView(
            this@CategoryDetailsActivity,
            R.layout.activity_category_details
        )
        setSupportActionBar(toolbar)

        iv_back.setOnClickListener { onBackPressed() }

        contentView.viewModel = ViewModelProviders.of(
            this@CategoryDetailsActivity,
            CategoryDetailsViewModelFactory(
                WeakReference(applicationContext),
                intent.getStringExtra(KEY_CATEGORY_NAME),
                intent.getStringExtra(KEY_CATEGORY_IMAGE),
                intent.getStringExtra(KEY_CATEGORY_DESCRIPTION)
            )
        ).get(CategoryDetailsViewModel::class.java)


        mViewModel = contentView.viewModel!!

        mViewModel.mResponseLD.observe(this, Observer {
            mViewModel.setNewData(it as MutableList)
            mViewModel.dismissLoading()
        })
        mViewModel.mErrorLD.observe(this, Observer {
            mViewModel.dismissLoading()
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    override fun onStart() {
        super.onStart()
        mViewModel.categoryName = intent.getStringExtra(KEY_CATEGORY_NAME)
        mViewModel.categoryImage = intent.getStringExtra(KEY_CATEGORY_IMAGE)
        mViewModel.categoryDescription = intent.getStringExtra(KEY_CATEGORY_DESCRIPTION)
        mViewModel.onStart()
    }


}
