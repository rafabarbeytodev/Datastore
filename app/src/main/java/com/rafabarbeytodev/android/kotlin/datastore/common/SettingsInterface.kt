package com.rafabarbeytodev.android.kotlin.datastore.common

import com.rafabarbeytodev.android.kotlin.datastore.data.model.UserPreferences
import kotlinx.coroutines.flow.Flow

/*****
 * Proyect: Datastore
 * Package: com.rafabarbeytodev.android.kotlin.datastore.common
 *
 * Created by Rafael Barbeyto Torrellas on 26/12/2022 at 16:32
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 *
 * All rights reserved 2022.
 *****/
interface SettingsInterface {

    suspend fun saveDataStore(userPreferences: UserPreferences)
    suspend fun readDataStore(): Flow<UserPreferences>
}