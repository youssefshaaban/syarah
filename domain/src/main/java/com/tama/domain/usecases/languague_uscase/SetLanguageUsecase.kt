package com.tama.domain.usecases.languague_uscase

import com.tama.domain.repository.ISharedPrefrance
import javax.inject.Inject

class SetLanguageUsecase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {

    operator fun invoke(lang:String){
        iSharedPrefrance.setLang(lang)
    }
}