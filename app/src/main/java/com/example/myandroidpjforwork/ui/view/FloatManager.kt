package com.example.myandroidpjforwork.ui.view

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.core.view.contains
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver


@SuppressLint("StaticFieldLeak")
object FloatManager {

    private lateinit var mContentView: FrameLayout
    private var mActivity: ComponentActivity? = null
    private var mFloatView: BaseFloatView? = null
    private var mIsShowing: Boolean = false

    fun with(activity: ComponentActivity): FloatManager {
        mContentView = activity.window.decorView.findViewById(android.R.id.content) as FrameLayout
        mActivity = activity
        addLifecycle(mActivity)
        return this
    }

    fun add(floatView: BaseFloatView): FloatManager {
        if (mIsShowing) return this
//        if (::mContentView.isInitialized && mContentView.contains(floatView)) {
//            mContentView.removeView(floatView)
//        }
        mFloatView = floatView
        return this
    }

    fun setClick(listener: BaseFloatView.OnFloatClickListener): FloatManager {
        mFloatView?.setOnFloatClickListener(listener)
        return this
    }

    fun show() {
        checkParams()
        if (!mIsShowing) {
            mContentView.removeView(mFloatView)
            mFloatView?.layoutParams  = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.END or Gravity.BOTTOM
            )
            mContentView.addView(mFloatView)
            mIsShowing = true
        }
    }

    private fun checkParams() {
        if (mActivity == null) {
            throw NullPointerException("You must set the 'Activity' params before the show()")
        }
        if (mFloatView == null) {
            throw NullPointerException("You must set the 'FloatView' params before the show()")
        }
    }

    private fun addLifecycle(activity: ComponentActivity?) {
        activity?.lifecycle?.addObserver(mLifecycleEventObserver)
    }

    private var mLifecycleEventObserver = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            hide()
        }
    }

    fun hide() {
        mIsShowing = false
        if (::mContentView.isInitialized && mFloatView != null && mContentView.contains(mFloatView!!)) {
            mContentView.removeView(mFloatView)
        }
        mFloatView?.release()
        mFloatView = null
        mActivity?.lifecycle?.removeObserver(mLifecycleEventObserver)
        mActivity = null
    }
}