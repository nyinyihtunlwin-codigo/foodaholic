package com.nyinyihtunlwin.projects.sharedmodule.ui

import android.arch.lifecycle.Lifecycle.Event
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

open class BaseFragment : Fragment(), Observer<Error> {


    private var viewLifecycleOwner: ViewLifecycleOwner? = null

    internal class ViewLifecycleOwner : LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)

        override fun getLifecycle(): LifecycleRegistry {
            return lifecycleRegistry
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner = ViewLifecycleOwner()
        viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this!!)) {
            EventBus.getDefault().register(this)
        }
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_START)
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_RESUME)
        }
    }

    override fun onPause() {
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_PAUSE)
        }
        super.onPause()
    }

    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this!!)) {
            EventBus.getDefault().unregister(this!!)
        }
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_STOP)
        }
        super.onStop()
    }

    override fun onDestroyView() {
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_DESTROY)
            viewLifecycleOwner = null
        }
        super.onDestroyView()
    }

    override fun onChanged(error: Error?) {

    }

    @Subscribe
    fun onEvent(event: Any?) {

    }
}