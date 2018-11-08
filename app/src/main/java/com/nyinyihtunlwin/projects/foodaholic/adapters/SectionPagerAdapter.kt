package com.nyinyihtunlwin.projects.foodaholic.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nyinyihtunlwin.projects.foodaholic.fragments.LatestFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LatestFragment()
            }
            1 -> {
                LatestFragment()
            }
            else -> {
                LatestFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}