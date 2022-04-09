package com.yang.kotlinmvvmsample.app

import android.app.Application
import com.yang.baselibs.config.AppConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initApp()
    }

    private fun initApp() {
        AppConfig.init(this)
    }

}