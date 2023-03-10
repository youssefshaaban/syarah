package com.tama.syarah.home.settings


import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.syarah.R
import com.tama.syarah.account_info.AccountInformationActivity
import com.tama.syarah.change_language.ChangeLanguageActivity
import com.tama.syarah.di.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {


    val clickItemSetting: (View,SettingsItem) -> Unit = { view,settingItem ->
        settingItem.cls?.let { view.context.startActivity(Intent(view.context,settingItem.cls)) }
    }

    val settingsItems = MutableLiveData<List<SettingsItem>>()

    init {
        settingsItems.value = getDataForSettings()
    }

    private fun getDataForSettings(): List<SettingsItem> {
        return listOf(
            SettingsItem(resourcesProvider.getString(R.string.account_information_menu), R.drawable.ic_edit, cls = AccountInformationActivity::class.java),
            SettingsItem(resourcesProvider.getString(R.string.change_language_menu), R.drawable.ic_language, cls = ChangeLanguageActivity::class.java)
        )
    }
}