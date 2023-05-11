package com.tama.domain.usecases.languague_uscase

import com.tama.domain.repositories.ISharedPrefrance
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {

    operator fun invoke(lang:String){
        iSharedPrefrance.setLang(lang)
    }
}