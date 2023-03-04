package com.tama.syarah.home.settings


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.syarah.R
import com.tama.syarah.di.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {


    val clickItemSetting: (SettingsItem) -> Unit = { settingItem -> }

    val settingsItems = MutableLiveData<List<SettingsItem>>()

    init {
        settingsItems.value = getDataForSettings()
    }

    private fun getDataForSettings(): List<SettingsItem> {
        return listOf(
            SettingsItem(resourcesProvider.getString(R.string.account_information_menu), R.drawable.ic_edit),
            SettingsItem(resourcesProvider.getString(R.string.change_language_menu), R.drawable.ic_language)
        )
    }
}