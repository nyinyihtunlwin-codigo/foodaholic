package com.nyinyihtunlwin.projects.foodaholic.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.FragmentLatestBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.LatestViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.LatestViewModelFactory
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseFragment
import java.lang.ref.WeakReference

class LatestFragment : BaseFragment() {

    lateinit var viewModel: LatestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView :FragmentLatestBinding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_latest, container, false)
        inflatedView.viewModel = ViewModelProviders.of(
            this@LatestFragment,
            LatestViewModelFactory(WeakReference(activity!!.applicationContext))
        ).get(LatestViewModel::class.java)

        viewModel = inflatedView.viewModel!!

        viewModel.mResponseLD.observe(this, Observer {
            viewModel.setNewData(it as MutableList)
            viewModel.dismissLoading()
        })
        viewModel.mErrorLD.observe(this, Observer {
            viewModel.dismissLoading()
            Toast.makeText(activity!!.applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })

        return inflatedView.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

}
