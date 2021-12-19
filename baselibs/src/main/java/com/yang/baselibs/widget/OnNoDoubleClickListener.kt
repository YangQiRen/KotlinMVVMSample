package com.yang.baselibs.widget

import android.view.View

abstract class OnNoDoubleClickListener : View.OnClickListener {

    private var mTimeInterval: Long = 1000
    private var mLastClickTime: Long = 0

    constructor()
    constructor(timeInterval: Long) {
        this.mTimeInterval = timeInterval
    }

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - mLastClickTime > mTimeInterval) {
            mLastClickTime = currentTime
            onNoDoubleClick(v)
        }
    }

    abstract fun onNoDoubleClick(v: View?)
}