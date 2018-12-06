package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityInfoBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.InfoViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.InfoViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_bookmark.*
import java.lang.ref.WeakReference

class InfoActivity : BaseActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, InfoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView: ActivityInfoBinding = DataBindingUtil.setContentView(
            this@InfoActivity,
            R.layout.activity_info
        )
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        contentView.viewModel = ViewModelProviders.of(
            this@InfoActivity,
            InfoViewModelFactory(WeakReference(applicationContext))
        ).get(InfoViewModel::class.java)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
