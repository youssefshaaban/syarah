package com.tama.syarah.onboarding

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.domain.usecases.onboarding_uscases.IsOnboaedingOpenUscase
import com.tama.domain.usecases.onboarding_uscases.SetOnboardingUsecase
import com.tama.syarah.login.LoginActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingUsecase: SetOnboardingUsecase,
    private val isOnboaedingOpenUscase: IsOnboaedingOpenUscase
) : ViewModel() {
    val finshiViewLiveData = MutableLiveData(false)
    val goLogin = MutableLiveData<Boolean>()

    init {
        goLogin.value = isOnboaedingOpenUscase.invoke()
    }

    val onClickGoLogin: (View) -> Unit = { view ->
        view.context.startActivity(Intent(view.context, LoginActivity::class.java))
        setOnboardingUsecase.invoke(true)
        finshiViewLiveData.value = true
    }
//    val onClickSkip:(View)->Unit = { view ->
//        view.context.startActivity(Intent(view.context,LoginActivity::class.java))
//
//        finshiViewLiveData.value=true
//    }
//    val onClickContinue:(View)->Unit = { view ->
//        view.context.startActivity(Intent(view.context,LoginActivity::class.java))
//        finshiViewLiveData.value=true
//    }
}