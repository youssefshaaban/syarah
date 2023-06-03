package com.tama.car_center.home.settings


import android.content.Context
import android.content.res.loader.ResourcesProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.car_center.home.settings.account_info.AccountInformationActivity
import com.tama.car_center.home.settings.change_language.ChangeLanguageSettingActivity
import com.tama.car_center.util.SingleLiveDataEvent
import com.tama.syarah.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
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