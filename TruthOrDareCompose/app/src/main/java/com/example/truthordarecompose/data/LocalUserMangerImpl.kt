package com.example.truthordarecompose.data

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.truthordarecompose.data.utils.Constants
import com.example.truthordarecompose.data.utils.Constants.USER_SETTINGS
import com.example.truthordarecompose.domain.manger.LocalUserManger
import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

class LocalUserMangerImpl @Inject constructor(
    private val application: Application
) : LocalUserManger {

    override suspend fun saveAppEntry() {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override suspend fun saveTypeMode(typeMode: TypeMode) {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.TYPE_MODE] = typeMode.name
        }
    }

    override fun readTypeMode(): Flow<TypeMode?> {
        return application.dataStore.data.map { preferences ->
            val typeModeName = preferences[PreferenceKeys.TYPE_MODE]
            typeModeName?.let { TypeMode.valueOf(it) }
        }
    }
    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }
}

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    val TYPE_MODE = stringPreferencesKey("type_mode")
}