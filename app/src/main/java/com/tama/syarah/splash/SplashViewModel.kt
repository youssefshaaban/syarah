package com.tama.syarah.splash


import android.os.Handler
import android.os.Looper
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

    init {
        Handler(Looper.getMainLooper()).postDelayed(Runnable { //This method will be executed once the timer is over
            if (isLanguageLanguageSelctUseCase.invoke() && !isOnboaedingOpenUscase.invoke()) {
                openOnBoarding.value = true
            } else if (isLanguageLanguageSelctUseCase.invoke() && isOnboaedingOpenUscase.invoke()) {
                openLogin.value
            } else
                openLanguageSelection.value = true
        }, 2000)
    }
}