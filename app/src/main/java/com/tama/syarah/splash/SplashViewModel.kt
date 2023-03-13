package com.tama.syarah.splash



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.domain.usecases.languague_uscase.IsLanguageLanguageSelctUseCase
import com.tama.domain.usecases.onboarding_uscases.IsOnboaedingOpenUscase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isOnboaedingOpenUscase: IsOnboaedingOpenUscase,
    private val isLanguageLanguageSelctUseCase: IsLanguageLanguageSelctUseCase
) : ViewModel() {
    val openOnBoarding = MutableLiveData<Boolean>()
    val openLanguageSelection = MutableLiveData<Boolean>()
    val openLogin = MutableLiveData<Boolean>()

    fun initNextView(){
        if (isLanguageLanguageSelctUseCase.invoke() && !isOnboaedingOpenUscase.invoke()) {
            openOnBoarding.postValue(true)
        } else if (isLanguageLanguageSelctUseCase.invoke() && isOnboaedingOpenUscase.invoke()) {
            openLogin.postValue(true)
        } else
            openLanguageSelection.postValue(true)
    }
}