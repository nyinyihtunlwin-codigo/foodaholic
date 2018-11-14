package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityMealDetailsBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.MealDetailsViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.MealDetailsViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_meal_details.*
import java.lang.ref.WeakReference

class MealDetailsActivity : BaseActivity() {

    companion object {
        private const val KEY_MEAL_ID = "KEY_MEAL_ID"
        fun newInstnace(context: Context, mealId: String): Intent {
            var intentToMealDetailsActivity = Intent(context, MealDetailsActivity::class.java)
            intentToMealDetailsActivity.putExtra(KEY_MEAL_ID, mealId)
            return intentToMealDetailsActivity
        }
    }

    lateinit var mViewModel: MealDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView: ActivityMealDetailsBinding = DataBindingUtil.setContentView(
            this@MealDetailsActivity,
            R.layout.activity_meal_details
        )
        setSupportActionBar(toolbar)
        var isShow = true
        var scrollRange = -1
        appbar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            run {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    fab_play.visibility = View.GONE
                    isShow = true
                } else if (isShow) {
                    fab_play.visibility = View.VISIBLE
                    isShow = false

                }
            }
        }
        iv_back.setOnClickListener { onBackPressed() }

        contentView.viewModel = ViewModelProviders.of(
            this@MealDetailsActivity,
            MealDetailsViewModelFactory(WeakReference(applicationContext), intent.getStringExtra(KEY_MEAL_ID))
        ).get(MealDetailsViewModel::class.java)


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
        mViewModel.mealId = intent.getStringExtra(KEY_MEAL_ID)
        mViewModel.onStart()
    }


}
