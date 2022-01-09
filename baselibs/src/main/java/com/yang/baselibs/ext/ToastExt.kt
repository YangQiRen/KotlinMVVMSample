package com.yang.baselibs.ext

import com.yang.baselibs.config.AppConfig
import com.yang.baselibs.widget.CustomToast

/**
 * toast extension
 */
fun showToast(content: String = "") {
    CustomToast(AppConfig.getApplication(), content).show()
}