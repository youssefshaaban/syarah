package com.tama.syarah.splash



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tama.domain.usecases.languague_uscase.IsLanguageLanguageSelctUseCase
import com.tama.domain.usecases.onboarding_uscases.IsOnboaedingOpenUscase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isOnboaedingOpenUscase: IsOnboaedingOpenUscase,
    private val isLanguageLanguageSelctUseCase: IsLanguageLanguageSelctUseCase
) : ViewModel() {
    val openOnBoarding = MutableLiveData<Boolean>()
    val openLanguageSelection = MutableLiveData<Boolean>()
    val openLogin = MutableLiveData<Boolean>()
    var dataLoaded: Boolean = false
    fun initNextView(){
        viewModelScope.launch {
            delay(2000)
            dataLoaded = true
            if (isLanguageLanguageSelctUseCase.invoke() && !isOnboaedingOpenUscase.invoke()) {
                openOnBoarding.postValue(true)
            } else if (isLanguageLanguageSelctUseCase.invoke() && isOnboaedingOpenUscase.invoke()) {
                openLogin.postValue(true)
            } else
                openLanguageSelection.postValue(true)
        }
    }
}