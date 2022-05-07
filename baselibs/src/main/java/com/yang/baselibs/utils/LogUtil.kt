package com.yang.baselibs.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.yang.baselibs.config.AppConfig

object LogUtil {

    private var debug = AppConfig.debug
    init {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    fun i(content: String) {
        if (debug) {
            Logger.i(content)
        }
    }

    fun v(content: String) {
        if (debug) {
            Logger.v(content)
        }
    }

    fun d(content: String) {
        if (debug) {
            Logger.d(content)
        }
    }

    fun w(content: String) {
        if (debug) {
            Logger.w(content)
        }
    }

    fun e(content: String) {
        if (debug) {
            Logger.e(content)
        }
    }

}