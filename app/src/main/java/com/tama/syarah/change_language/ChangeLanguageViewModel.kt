package com.tama.syarah.change_language

import android.content.Intent
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.domain.usecases.languague_uscase.GetLanguageUseCase
import com.tama.domain.usecases.languague_uscase.SetLanguageLanguageSelctUseCase
import com.tama.domain.usecases.languague_uscase.SetLanguageUseCase
import com.tama.syarah.login.LoginActivity
import com.tama.syarah.onboarding.OnboardingActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeLanguageViewModel @Inject constructor(
    val setLanguageUsecase: SetLanguageUseCase,
    getLanguageUseCase: GetLanguageUseCase,
    val setLanguageLanguageSelctUseCase: SetLanguageLanguageSelctUseCase
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
        if (languageType == LanguageType.ENGLISH) {
            _englishSelection.value = true
            _arabicSelection.value = false
            setLanguageUsecase.invoke("en")
        } else if (languageType == LanguageType.ARABIC) {
            _englishSelection.value = false
            _arabicSelection.value = true
            setLanguageUsecase.invoke("ar")
        }
        setLanguageLanguageSelctUseCase.invoke(true)
        view.context.startActivity(Intent(view.context, OnboardingActivity::class.java))
        shouldFinishActiivty.value = true
    }
}

enum class LanguageType() {
    ENGLISH, ARABIC
}