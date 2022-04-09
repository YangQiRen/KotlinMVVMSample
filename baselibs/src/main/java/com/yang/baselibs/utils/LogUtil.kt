package com.yang.baselibs.utils

import com.orhanobut.logger.Logger
import com.yang.baselibs.config.AppConfig

object LogUtil {

    private var debug = AppConfig.debug

    fun i(tag: String, content: String) {
        if (debug) {
            Logger.i(tag, content)
        }
    }

    fun i(content: String) {
        i(AppConfig.TAG, content)
    }

    fun v(tag: String, content: String) {
        if (debug) {
            Logger.v(tag, content)
        }
    }

    fun v(content: String) {
        v(AppConfig.TAG, content)
    }

    fun d(tag: String, content: String) {
        if (debug) {
            Logger.d(tag, content)
        }
    }

    fun d(content: String) {
        d(AppConfig.TAG, content)
    }

    fun w(tag: String, content: String) {
        if (debug) {
            Logger.w(tag, content)
        }
    }

    fun w(content: String) {
        w(AppConfig.TAG, content)
    }

    fun e(tag: String, content: String) {
        if (debug) {
            Logger.e(tag, content)
        }
    }

    fun e(content: String) {
        e(AppConfig.TAG, content)
    }

}