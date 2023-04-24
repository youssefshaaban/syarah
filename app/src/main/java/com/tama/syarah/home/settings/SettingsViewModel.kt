package com.tama.syarah.home.settings


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.syarah.R
import com.tama.syarah.home.settings.account_info.AccountInformationActivity
import com.tama.syarah.di.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {
    val itemSelection = MutableLiveData<SettingsItem>()
    val clickItemSetting: (SettingsItem) -> Unit = { settingItem ->
        itemSelection.value = settingItem
    }

    val settingsItems = MutableLiveData<List<SettingsItem>>()

    init {
        settingsItems.value = getDataForSettings()
    }

    private fun getDataForSettings(): List<SettingsItem> {
        return listOf(
            SettingsItem(
                resourcesProvider.getString(R.string.account_information_menu),
                R.drawable.ic_edit,
                cls = AccountInformationActivity::class.java
            ),
            SettingsItem(
                resourcesProvider.getString(R.string.change_language_menu),
                R.drawable.ic_language,
                cls = com.tama.syarah.home.settings.change_language.ChangeLanguageSettingActivity::class.java
            )
        )
    }
}