package com.yang.baselibs.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val USER_DATASTORE = "datastore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(USER_DATASTORE)

class DataStoreManager constructor(val context: Context) {

    /**
     * save function
     * 需要存什麼寫什麼，不用像以前putString, putInt..都寫
     */
    suspend fun saveName(name: String) {
        context.dataStore.edit {
            it[NAME] = name
        }
    }

    /**
     * get function
     * 需要存什麼寫什麼，不用像以前getString, getInt..都寫
     */
    fun getName() = context.dataStore.data.map { it[NAME] ?: "" }

    /**
     * remove function
     */
    suspend fun removeName() {
        context.dataStore.edit {
            it.remove(NAME)
        }
    }

    suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }


//    // example for obejct
//    suspend fun savetoDataStore(phoneBook: PhoneBook) {
//        context.dataStore.edit {
//            it[NAME] = phoneBook.name
//            it[ADDRESS] = phoneBook.address
//            it[PHONE_NUMBER] = phoneBook.phone
//        }
//    }
//
//    suspend fun getFromDataStore() = context.dataStore.data.map {
//        PhoneBook(
//            name = it[NAME] ?: "",
//            address = it[ADDRESS] ?: "",
//            phone = it[PHONE_NUMBER] ?: ""
//        )
//    }

    companion object {
        val NAME = stringPreferencesKey("NAME")
        val ADDRESS = stringPreferencesKey("ADDRESS")
        val PHONE_NUMBER = stringPreferencesKey("PHONE_NUMBER")
    }
}