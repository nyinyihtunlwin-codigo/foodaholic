package com.nyinyihtunlwin.projects.foodaholic.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.adapters.IngredientsRecyAdapter
import kotlinx.android.synthetic.main.activity_meal_details.*

class MealDetailsActivity : AppCompatActivity() {


    companion object {
        private const val KEY_MEAL_ID = "KEY_MEAL_ID"
        fun newInstnace(context: Context, mealId: String): Intent {
            var intentToMealDetailsActivity = Intent(context, MealDetailsActivity::class.java)
            intentToMealDetailsActivity.putExtra(KEY_MEAL_ID, mealId)
            return intentToMealDetailsActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)
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
        rv_ingredients.adapter = IngredientsRecyAdapter(this@MealDetailsActivity)
        rv_ingredients.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        iv_back.setOnClickListener { onBackPressed() }
    }

}
