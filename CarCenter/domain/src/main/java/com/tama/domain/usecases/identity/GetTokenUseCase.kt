package com.tama.domain.usecases.identity

import com.tama.domain.repositories.ISharedPrefrance
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val iSharedPrefrance: ISharedPrefrance) {
    operator fun invoke(): String? {
        return iSharedPrefrance.getToken()
    }
}