package com.yang.kotlinmvvmsample.app

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.yang.baselibs.config.AppConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initApp()

        initLogger()
    }

    private fun initApp() {
        AppConfig.init(this)
        AppConfig.openDebug()
    }

    /**
     * 初始化log日志打印
     */
    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("mvvm")
            .methodCount(1)
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Logger.addLogAdapter(DiskLogAdapter())
    }

}