package com.tama.domain.usecases.languague_uscase

import com.tama.domain.repository.ISharedPrefrance
import javax.inject.Inject

class GetLanguageUscase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {
    operator fun invoke():String = iSharedPrefrance.getLang()
}