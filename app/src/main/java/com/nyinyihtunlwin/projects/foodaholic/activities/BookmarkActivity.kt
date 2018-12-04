package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityBookmarkBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.BookmarkViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.BookmarkViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_bookmark.*
import java.lang.ref.WeakReference

class BookmarkActivity : BaseActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, BookmarkActivity::class.java)
        }
    }

    lateinit var mViewModel: BookmarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView: ActivityBookmarkBinding = DataBindingUtil.setContentView(
            this@BookmarkActivity,
            R.layout.activity_bookmark
        )
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        contentView.viewModel = ViewModelProviders.of(
            this@BookmarkActivity,
            BookmarkViewModelFactory(WeakReference(applicationContext))
        ).get(BookmarkViewModel::class.java)

        mViewModel = contentView.viewModel!!

        mViewModel.mResponseLD.observe(this, Observer {
            mViewModel.setNewData(it as MutableList)
        })
        mViewModel.mErrorLD.observe(this, Observer {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })
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

    override fun onStart() {
        super.onStart()
        mViewModel.onStart()
    }

}
