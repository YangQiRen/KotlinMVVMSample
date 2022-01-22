package com.yang.baselibs.ext

import android.view.View
import com.yang.baselibs.widget.OnSingleClickListener

inline fun <T: View> T.setOnSingleClickListener(timeInterval: Long = 1000, crossinline block: (T) -> Unit) {
    setOnClickListener(object : OnSingleClickListener(timeInterval){
        override fun onSingleClick(v: View?) {
            block(this@setOnSingleClickListener)
        }
    })
}