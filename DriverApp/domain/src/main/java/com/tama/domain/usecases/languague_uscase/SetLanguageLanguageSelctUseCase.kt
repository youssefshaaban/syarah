package com.tama.domain.usecases.languague_uscase

import com.tama.domain.repositories.ISharedPrefrance
import javax.inject.Inject

class SetLanguageLanguageSelctUseCase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {

    operator fun invoke(boolean: Boolean){
        iSharedPrefrance.setLanguageSelected(boolean)
    }
}