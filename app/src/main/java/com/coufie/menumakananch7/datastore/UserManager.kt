package com.coufie.menumakananch7.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    private val userDataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object{
        val ID = preferencesKey<String>("USER_ID")
        val EMAILL = preferencesKey<String>("USER_EMAILL")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val USERNAME = preferencesKey<String>("USER_USERNAME")

    }

    suspend fun saveLoginData(id: String, email : String, password:String, username:String){
        userDataStore.edit{
            it[ID] = id
            it[EMAILL] = email
            it[PASSWORD] = password
            it[USERNAME] = username
        }
    }

    suspend fun deleteUserData(){
        userDataStore.edit{
            it.clear()
        }
    }


    val userId : Flow<String> = userDataStore.data.map {
        it[ID] ?: ""
    }

    val userEmail : Flow<String> = userDataStore.data.map{
        it[EMAILL] ?: ""
    }

    val userPassword : Flow<String> = userDataStore.data.map {
        it[PASSWORD] ?: ""
    }

    val userUsername : Flow<String> = userDataStore.data.map {
        it[USERNAME] ?: ""
    }

}