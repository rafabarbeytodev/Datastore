package com.rafabarbeytodev.android.kotlin.datastore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafabarbeytodev.android.kotlin.datastore.common.SettingsRepository
import com.rafabarbeytodev.android.kotlin.datastore.data.model.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*****
 * Proyect: Datastore
 * Package: com.rafabarbeytodev.android.kotlin.datastore.ui.viewmodel
 *
 * Created by Rafael Barbeyto Torrellas on 27/12/2022 at 12:50
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2022.
 *****/
@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val mSettingsRepository: SettingsRepository
) : ViewModel() {

    var destinationMail: MutableLiveData<String> = MutableLiveData("")
    var destinationTelephone: MutableLiveData<String> = MutableLiveData("")
    var activation: MutableLiveData<Boolean> = MutableLiveData(true)
    var filterWords: MutableLiveData<Boolean> = MutableLiveData(false)
    var keywords: MutableLiveData<String> = MutableLiveData("")
    var filterSenders: MutableLiveData<Boolean> = MutableLiveData(false)
    var sendersFiltered: MutableLiveData<String> = MutableLiveData("")

    var userPreferences: MutableLiveData<UserPreferences> = MutableLiveData()

    fun getFilterSender(): LiveData<Boolean> {
        return filterSenders
    }

    fun getFilterWord(): LiveData<Boolean> {
        return filterWords
    }

    fun saveDataStore() {
        viewModelScope.launch(Dispatchers.IO) {
            mSettingsRepository.saveDataStore(
                UserPreferences(
                    destinationMail = destinationMail.value.orEmpty(),
                    destinationTelephone = destinationTelephone.value.orEmpty(),
                    activation = activation.value ?: true,
                    filterWords = filterWords.value ?: false,
                    keywords = keywords.value.orEmpty(),
                    filterSenders = filterSenders.value ?: false,
                    sendersFiltered = sendersFiltered.value.orEmpty()
                )
            )
        }
    }

    fun getDataStore() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mSettingsRepository.readDataStore().collect {
                    userPreferences.postValue(it)
                }
            } catch (e: Exception) {
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (e is java.lang.NullPointerException) {
                    userPreferences.value?.destinationMail = ""
                } else {
                    throw e
                }
            }
        }
    }

}