package com.yang.baselibs.utils

import android.content.Context
import android.content.SharedPreferences
import com.yang.baselibs.config.AppConfig
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SharedPreferencesUtils {

    companion object {
        private const val FILE_NAME = "shared_file"
    }

    private val preferences: SharedPreferences by lazy {
        AppConfig.getApplication().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }
    var token by SharedPreferenceDelegates.string(defaultValue = "")

    var domain by SharedPreferenceDelegates.string(defaultValue = "")

    var username by SharedPreferenceDelegates.string(defaultValue = "this is username")

    var age by SharedPreferenceDelegates.int()

    var flag by SharedPreferenceDelegates.boolean()

    var currentDateTime: Long by SharedPreferenceDelegates.long(System.currentTimeMillis())

    var money by SharedPreferenceDelegates.float()

    var setString by SharedPreferenceDelegates.setString()

    private object SharedPreferenceDelegates {

        fun int(defaultValue: Int = 0) = object : ReadWriteProperty<SharedPreferencesUtils, Int> {

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Int {
                return thisRef.preferences.getInt(property.name, defaultValue)
            }

            override fun setValue(
                thisRef: SharedPreferencesUtils,
                property: KProperty<*>,
                value: Int
            ) {
                thisRef.preferences.edit().putInt(property.name, value).apply()
            }
        }

        fun long(defaultValue: Long = 0L) = object :
            ReadWriteProperty<SharedPreferencesUtils, Long> {

            override fun getValue(thisRef: SharedPreferencesUtils, property: KProperty<*>): Long {
                return thisRef.preferences.getLong(property.name, defaultValue)
            }

            override fun setValue(
                thisRef: SharedPreferencesUtils,
                property: KProperty<*>,
                value: Long
            ) {
                thisRef.preferences.edit().putLong(property.name, value).apply()
            }
        }

        fun boolean(defaultValue: Boolean = false) =
            object : ReadWriteProperty<SharedPreferencesUtils, Boolean> {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ): Boolean {
                    return thisRef.preferences.getBoolean(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: Boolean
                ) {
                    thisRef.preferences.edit().putBoolean(property.name, value).apply()
                }
            }

        fun float(defaultValue: Float = 0.0f) =
            object : ReadWriteProperty<SharedPreferencesUtils, Float> {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ): Float {
                    return thisRef.preferences.getFloat(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: Float
                ) {
                    thisRef.preferences.edit().putFloat(property.name, value).apply()
                }
            }

        fun string(defaultValue: String? = null) =
            object : ReadWriteProperty<SharedPreferencesUtils, String?> {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ): String? {
                    return thisRef.preferences.getString(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: String?
                ) {
                    thisRef.preferences.edit().putString(property.name, value).apply()
                }
            }

        fun setString(defaultValue: Set<String>? = null) =
            object : ReadWriteProperty<SharedPreferencesUtils, Set<String>?> {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ): Set<String>? {
                    return thisRef.preferences.getStringSet(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: Set<String>?
                ) {
                    thisRef.preferences.edit().putStringSet(property.name, value).apply()
                }
            }
    }
}