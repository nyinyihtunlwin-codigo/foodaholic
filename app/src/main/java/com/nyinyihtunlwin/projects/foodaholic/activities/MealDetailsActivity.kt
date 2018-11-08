package com.nyinyihtunlwin.projects.foodaholic.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nyinyihtunlwin.projects.foodaholic.R
import kotlinx.android.synthetic.main.activity_meal_details.*

class MealDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)
        setSupportActionBar(toolbar)
    }

}
