package com.tama.domain.usecases.languague_uscase

import com.tama.domain.repository.ISharedPrefrance
import javax.inject.Inject

class SetLanguageLanguageSelctUseCase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {

    operator fun invoke(boolean: Boolean){
        iSharedPrefrance.setLanguageSelected(boolean)
    }
}