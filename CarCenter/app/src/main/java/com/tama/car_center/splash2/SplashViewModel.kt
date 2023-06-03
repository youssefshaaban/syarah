package com.tama.car_center.splash2


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tama.domain.usecases.identity.GetTokenUseCase
import com.tama.domain.usecases.languague_uscase.IsLanguageLanguageSelctUseCase
import com.tama.domain.usecases.onboarding_uscases.IsOnboaedingOpenUscase
import com.tama.car_center.util.SingleLiveDataEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isOnboaedingOpenUscase: IsOnboaedingOpenUscase,
    private val isLanguageLanguageSelctUseCase: IsLanguageLanguageSelctUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    val openOnBoarding = MutableLiveData<SingleLiveDataEvent<Boolean>>()
    val openLanguageSelection = MutableLiveData<SingleLiveDataEvent<Boolean>>()
    val openLogin = MutableLiveData<SingleLiveDataEvent<Boolean>>()
    val openHome = MutableLiveData<SingleLiveDataEvent<Boolean>>()
    fun initNextView() {
        viewModelScope.launch {
            delay(2000)
            if (!getTokenUseCase.invoke().isNullOrEmpty()) {
                openHome.value = SingleLiveDataEvent(true)
            } else if (isLanguageLanguageSelctUseCase.invoke() && !isOnboaedingOpenUscase.invoke()) {
                openOnBoarding.postValue(SingleLiveDataEvent(true))
            } else if (isLanguageLanguageSelctUseCase.invoke() && isOnboaedingOpenUscase.invoke()) {
                openLogin.postValue(SingleLiveDataEvent(true))
            } else
                openLanguageSelection.postValue(SingleLiveDataEvent(true))
        }
    }
}