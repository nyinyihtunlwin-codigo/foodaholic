package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivitySearchBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.SearchViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.SearchViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.ref.WeakReference

class SearchActivity : BaseActivity() {

    companion object {
        fun newInstnace(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    lateinit var mViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView: ActivitySearchBinding = DataBindingUtil.setContentView(
            this@SearchActivity,
            R.layout.activity_search
        )
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        contentView.viewModel = ViewModelProviders.of(
            this@SearchActivity,
            SearchViewModelFactory(WeakReference(applicationContext))
        ).get(SearchViewModel::class.java)

        mViewModel = contentView.viewModel!!

        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { textView, keyCode, keyEvent ->
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                if (et_search.text.toString() != "") {
                    val query = et_search.text.toString()
                    if (query != "") {
                        hideSoftKeyboard(applicationContext)
                        mViewModel.startSearchingMeals(query)
                    } else {
                        Toast.makeText(applicationContext, "Enter keywords.", Toast.LENGTH_SHORT).show()
                    }
                }
                return@OnEditorActionListener true
            }
            false
        })

        swipe_refresh.setOnRefreshListener { swipe_refresh.isRefreshing = false }



        mViewModel.mResponseLD.observe(this, Observer {
            mViewModel.setNewData(it as MutableList)
            mViewModel.dismissLoading()
        })
        mViewModel.mErrorLD.observe(this, Observer {
            mViewModel.dismissLoading()
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
