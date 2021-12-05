package com.yang.baselibs.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/*
    Activity管理器
 */
class AppManager private constructor(){

    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    /*
        Activity入棧
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /*
        Activity出棧
     */
    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /*
        獲取當前棧頂
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /*
        清理棧
     */
    fun finishAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /*
        退出應用程序
     */
    fun exitApp(context: Context){
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}
