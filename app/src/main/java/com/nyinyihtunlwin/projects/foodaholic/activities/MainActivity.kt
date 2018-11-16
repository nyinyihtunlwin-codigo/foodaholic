package com.nyinyihtunlwin.projects.foodaholic.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.adapters.SectionPagerAdapter
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityMainBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.MainViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.MainViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import java.lang.ref.WeakReference

class MainActivity : BaseActivity() {

    companion object {
        fun newInstnace(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contentView: ActivityMainBinding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )
        setSupportActionBar(toolbar)
        contentView.viewModel = ViewModelProviders.of(
            this@MainActivity,
            MainViewModelFactory(WeakReference(applicationContext))
        ).get(MainViewModel::class.java)

        val sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        vp_meal.adapter = sectionPagerAdapter

        vp_meal.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setSectionTitle(position)
            }
        })
        tabs.addOnTabSelectedListener(object : TabLayout.ViewPagerOnTabSelectedListener(vp_meal) {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_meal.currentItem = tab.position
                setSectionTitle(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })


    }

    private fun setSectionTitle(position: Int) {
        var sectionTitle = ""
        when (position) {
            0 -> sectionTitle = "Latest"
            1 -> sectionTitle = "Categories"
        }
        tv_section_name.text = sectionTitle
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

}
