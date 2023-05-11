package com.tama.domain.usecases.identity

import com.tama.domain.entity.AuthorizationData
import com.tama.domain.entity.LoginRequest
import com.tama.domain.repositories.IdentityRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class LoginUseCases @Inject constructor(private val identityRepository: IdentityRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<AuthorizationData> =
        identityRepository.login(LoginRequest(accountType = 4, email = email, password = password))
}