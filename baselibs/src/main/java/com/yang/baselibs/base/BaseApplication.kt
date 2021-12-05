package com.yang.baselibs.base

import android.app.Application
import android.content.Context

/*
    Application 基類
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    /*
        全局伴生對象
     */
    companion object {
        lateinit var context: Context
    }
}