package com.tama.domain.repositories

import com.tama.domain.entity.AuthorizationData
import com.tama.domain.entity.ChangePasswordRequest
import com.tama.domain.entity.LoginRequest
import com.tama.domain.entity.RefreshTokenRequest
import com.tama.domain.util.Resource

interface IdentityRepository {
    suspend fun login(loginRequest: LoginRequest): Resource<AuthorizationData>
    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest):Resource<AuthorizationData>
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest):Resource<Boolean>
}