package com.tama.domain.usecases.onboarding_uscases

import com.tama.domain.repository.ISharedPrefrance
import javax.inject.Inject

class SetOnboardingUsecase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {

    operator fun invoke(isOpen: Boolean){
        iSharedPrefrance.saveOnboarding(isOpen)
    }
}