package com.tama.syarah.change_language

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeLanguageViewModel @Inject constructor() : ViewModel() {
    val arabicSelection = MutableLiveData(true)

    val englishSelection = MutableLiveData(false)




    fun onClickSelectionLanguage(languageType: LanguageType) {
        if (languageType == LanguageType.ENGLISH) {
            englishSelection.value = true
            arabicSelection.value = false
        } else if (languageType == LanguageType.ARABIC) {
            englishSelection.value = false
            arabicSelection.value = true
        }
    }
}

enum class LanguageType() {
    ENGLISH, ARABIC
}