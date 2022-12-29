package com.rafabarbeytodev.android.kotlin.datastore.data.model

/*****
 * Proyect: Datastore
 * Package: com.rafabarbeytodev.android.kotlin.datastore.data
 *
 * Created by Rafael Barbeyto Torrellas on 27/09/2022 at 9:23
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2022.
 *****/
data class UserPreferences(
    var destinationMail: String = "",
    var destinationTelephone: String = "",
    var activation: Boolean = false,
    var filterWords: Boolean = false,
    var keywords: String = "",
    var filterSenders: Boolean = false,
    var sendersFiltered: String = ""
)
