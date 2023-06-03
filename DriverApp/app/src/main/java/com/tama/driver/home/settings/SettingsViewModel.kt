package com.tama.driver.home.settings


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.driver.R
import com.tama.driver.di.ResourcesProvider
import com.tama.driver.home.settings.account_info.AccountInformationActivity
import com.tama.driver.home.settings.change_language.ChangeLanguageSettingActivity

import com.tama.driver.util.SingleLiveDataEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {
    val itemSelection = MutableLiveData<SingleLiveDataEvent<SettingsItem>>()
    val clickItemSetting: (SettingsItem) -> Unit = { settingItem ->
        itemSelection.value = SingleLiveDataEvent(settingItem)
    }

    val settingsItems = MutableLiveData<List<SettingsItem>>()


    fun getDataForSettings(context: Context){
        settingsItems.value= listOf(
            SettingsItem(
                context.getString(R.string.account_information_menu),
                R.drawable.ic_edit,
                cls = AccountInformationActivity::class.java
            ),
            SettingsItem(
                context.getString(R.string.change_language_menu),
                R.drawable.ic_language,
                cls = ChangeLanguageSettingActivity::class.java
            )
        )
    }
}