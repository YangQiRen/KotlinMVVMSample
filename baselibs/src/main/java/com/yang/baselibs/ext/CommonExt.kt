package com.yang.baselibs.ext

import android.view.View

/**
 * 單點擊監聽器，防止重複點擊
 */
inline fun <T: View> T.setOnSingleClickListener(timeInterval: Long = 1000, crossinline block: (T) -> Unit) {
    setOnClickListener(object : OnSingleClickListener(timeInterval){
        override fun onSingleClick(v: View?) {
            block(this@setOnSingleClickListener)
        }
    })
}

abstract class OnSingleClickListener : View.OnClickListener {

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
            onSingleClick(v)
        }
    }

    abstract fun onSingleClick(v: View?)
}