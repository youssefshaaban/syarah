package com.tama.data.local

import android.content.Context
import com.tama.data.util.KEY_LANG_SELECTED
import com.tama.data.util.KEY_PREF_LANG
import com.tama.data.util.KEY_PREF_OPEN_ONBOARDING
import com.tama.data.util.SHARED_PREFERENCES_FILE_NAME
import com.tama.domain.repository.ISharedPrefrance
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPrefrancesImp @Inject constructor( @ApplicationContext context: Context) :
    ISharedPrefrance {
    private val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
    override fun isOnboardingOpen(): Boolean {
        return sharedPref.getBoolean(KEY_PREF_OPEN_ONBOARDING, false)
    }

    override fun saveOnboarding(open: Boolean) {
        sharedPref.edit().also {
            it.putBoolean(KEY_PREF_OPEN_ONBOARDING, open)
            it.apply()
        }
    }

    override fun getLang(): String {
        return sharedPref.getString(KEY_PREF_LANG, "").toString()
    }

    override fun setLang(lang: String) {
        sharedPref.edit().also {
            it.putString(KEY_PREF_LANG, lang)
            it.apply()
        }
    }

    override fun setLanguageSelected(isSelected: Boolean) {
        sharedPref.edit().also {
            it.putBoolean(KEY_LANG_SELECTED, isSelected)
            it.apply()
        }
    }

    override fun isLanguageSelected(): Boolean {
        return sharedPref.getBoolean(KEY_LANG_SELECTED, false)
    }
}