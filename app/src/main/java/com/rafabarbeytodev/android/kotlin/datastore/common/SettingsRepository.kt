package com.rafabarbeytodev.android.kotlin.datastore.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.rafabarbeytodev.android.kotlin.datastore.data.model.UserPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/*****
 * Proyect: Datastore
 * Package: com.rafabarbeytodev.android.kotlin.datastore.ui.viewmodel
 *
 * Created by Rafael Barbeyto Torrellas on 26/12/2022 at 16:31
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2022.
 *****/

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_PREFERENCES_NAME)

class SettingsRepository(private val context: Context) : SettingsInterface {

    companion object {
        val DESTINATION_MAIL = stringPreferencesKey("destinationMail")
        val DESTINATION_TELEPHONE = stringPreferencesKey("destinationTelephone")
        val ACTIVATION = booleanPreferencesKey("activation")
        val FILTER_WORD = booleanPreferencesKey("filterWords")
        val KEYWORDS = stringPreferencesKey("keywords")
        val FILTER_SENDERS = booleanPreferencesKey("filterSenders")
        val SENDERS_FILTERED = stringPreferencesKey("sendersFiltered")
    }

    override suspend fun saveDataStore(userPreferences: UserPreferences) {
        context.dataStore.edit { preferences ->
            preferences[DESTINATION_MAIL] = userPreferences.destinationMail
            preferences[DESTINATION_TELEPHONE] = userPreferences.destinationTelephone
            preferences[ACTIVATION] = userPreferences.activation
            preferences[FILTER_WORD] = userPreferences.filterWords
            preferences[KEYWORDS] = userPreferences.keywords
            preferences[FILTER_SENDERS] = userPreferences.filterSenders
            preferences[SENDERS_FILTERED] = userPreferences.sendersFiltered
        }
    }

    override suspend fun readDataStore() = context.dataStore.data.map { preferences ->
            UserPreferences(
                destinationMail = preferences[DESTINATION_MAIL]!!,
                destinationTelephone = preferences[DESTINATION_TELEPHONE]!!,
                activation = preferences[ACTIVATION]!!,
                filterWords = preferences[FILTER_WORD]!!,
                keywords = preferences[KEYWORDS]!!,
                filterSenders = preferences[FILTER_SENDERS]!!,
                sendersFiltered = preferences[SENDERS_FILTERED]!!
            )
        }

}

