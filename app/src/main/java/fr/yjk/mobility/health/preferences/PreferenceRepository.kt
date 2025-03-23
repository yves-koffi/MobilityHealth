package fr.yjk.mobility.health.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

class PreferenceRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    val auth: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[AUTH] ?: ""
        }

    private companion object {
        val AUTH = stringPreferencesKey("auth")
    }

    suspend fun saveAuthPreference(auth: String) {
        dataStore.edit { preferences ->
            preferences[AUTH] = auth
        }
    }
}