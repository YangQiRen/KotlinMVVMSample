package com.yang.baselibs.utils

import android.content.Context
import android.content.SharedPreferences
import com.yang.baselibs.base.BaseConstant
import com.yang.baselibs.config.AppConfig

/*
    SP工具類
 */
object AppPrefsUtils {
    private var sp: SharedPreferences =
        AppConfig.getApplication().getSharedPreferences(BaseConstant.TABLE_PREFS, Context.MODE_PRIVATE)
    private var ed: SharedPreferences.Editor = sp.edit()

    /*
        Boolean數據
     */
    fun putBoolean(key: String, value: Boolean) {
        ed.putBoolean(key, value)
        ed.commit()
    }

    /*
        默認 false
     */
    fun getBoolean(key: String, default: Boolean): Boolean {
        return sp.getBoolean(key, default)
    }

    /*
        String數據
     */
    fun putString(key: String, value: String) {
        ed.putString(key, value)
        ed.commit()
    }

    /*
        默認 ""
     */
    fun getString(key: String, default: String): String {
        return sp.getString(key, default) ?: default
    }

    /*
        Int數據
     */
    fun putInt(key: String, value: Int) {
        ed.putInt(key, value)
        ed.commit()
    }

    /*
        默認 0
     */
    fun getInt(key: String, default: Int): Int {
        return sp.getInt(key, default)
    }

    /*
        Long數據
     */
    fun putLong(key: String, value: Long) {
        ed.putLong(key, value)
        ed.commit()
    }

    /*
        默認 0
     */
    fun getLong(key: String, default: Long): Long {
        return sp.getLong(key, default)
    }

    /*
        Set數據
     */
    fun putStringSet(key: String, set: Set<String>) {
        val localSet = getStringSet(key)?.toMutableSet()
        localSet?.addAll(set)
        ed.putStringSet(key, localSet)
        ed.commit()
    }

    /*
        默認空set
     */
    fun getStringSet(key: String): MutableSet<String>? {
        val set = setOf<String>()
        return sp.getStringSet(key, set)
    }

    /*
        刪除key數據
     */
    fun remove(key: String) {
        ed.remove(key)
        ed.commit()
    }

    fun clear() {
        ed.clear()
        ed.commit()
    }

    /*
        檢查key與該數值是否存在
     */
    fun isExist(key: String): Boolean {
        return sp.contains(key)
    }
}
