package com.tama.domain.usecases.identity

import com.tama.domain.entity.ChangePasswordRequest
import com.tama.domain.repositories.IdentityRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class ChangePasswordUseCases @Inject constructor(private val identityRepository: IdentityRepository) {
    suspend operator fun invoke(currentPassword: String, newPassword: String): Resource<Boolean> =
        identityRepository.changePassword(ChangePasswordRequest(accountType = 4, newPassword = newPassword, currentPassword = currentPassword))
}