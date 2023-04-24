package com.tama.syarah.home.settings.change_language

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.domain.usecases.languague_uscase.GetLanguageUseCase
import com.tama.domain.usecases.languague_uscase.SetLanguageUseCase
import com.tama.syarah.onboarding.change_language.LanguageType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeLanViewModel @Inject constructor(
    val setLanguageUsecase: SetLanguageUseCase,
    val getLanguageUseCase: GetLanguageUseCase,
) : ViewModel() {
    private val _arabicSelection = MutableLiveData(true)
    private val _englishSelection = MutableLiveData(false)
    val englishSelection = MediatorLiveData<Boolean>()
    val arabicSelection = MediatorLiveData<Boolean>()
    val shouldFinishActiivty = MutableLiveData<Boolean>()

    init {
        englishSelection.addSource(_englishSelection) {
            englishSelection.value = it
        }
        arabicSelection.addSource(_arabicSelection) {
            arabicSelection.value = it
        }
        when (getLanguageUseCase.invoke()) {
            "en" -> {
                _englishSelection.value = true
                _arabicSelection.value = false
            }
            else -> {
                _arabicSelection.value = true
                _englishSelection.value = false
            }
        }
    }


    fun onClickSelectionLanguage(view: View, languageType: LanguageType) {
        if (languageType == LanguageType.ENGLISH && getLanguageUseCase.invoke() != "en") {
            _englishSelection.value = true
            _arabicSelection.value = false
            setLanguageUsecase.invoke("en")
            shouldFinishActiivty.value = true
        } else if (languageType == LanguageType.ARABIC && getLanguageUseCase.invoke() != "ar") {
            _englishSelection.value = false
            _arabicSelection.value = true
            setLanguageUsecase.invoke("ar")
            shouldFinishActiivty.value = true
        }

    }
}