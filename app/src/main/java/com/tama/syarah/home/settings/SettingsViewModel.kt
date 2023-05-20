package com.tama.syarah.home.settings


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.syarah.R
import com.tama.syarah.home.settings.account_info.AccountInformationActivity
import com.tama.syarah.di.ResourcesProvider
import com.tama.syarah.util.SingleLiveDataEvent
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
                cls = com.tama.syarah.home.settings.change_language.ChangeLanguageSettingActivity::class.java
            )
        )
    }
}