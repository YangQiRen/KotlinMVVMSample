package com.yang.baselibs.ext

import com.yang.baselibs.base.BaseApplication
import com.yang.baselibs.widget.CustomToast

/**
 * toast extension
 */
fun showToast(content: String = "") {
    CustomToast(BaseApplication.context, content).show()
}