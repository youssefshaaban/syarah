package com.tama.data.repositeries

import com.tama.data.remote.IdentityService
import com.tama.data.util.apiCall
import com.tama.domain.entity.AuthorizationData
import com.tama.domain.entity.ChangePasswordRequest
import com.tama.domain.entity.LoginRequest
import com.tama.domain.entity.RefreshTokenRequest
import com.tama.domain.repository.ISharedPrefrance
import com.tama.domain.repository.IdentityRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class IdentityRepositoryImp @Inject constructor(
    private val identityService: IdentityService,
    private val iSharedPrefrance: ISharedPrefrance
) : IdentityRepository {
    override suspend fun login(loginRequest: LoginRequest): Resource<AuthorizationData> {
        val result = apiCall {
            identityService.login(loginRequest)
        }
        return when (result) {
            is Resource.Success -> {
                val data = result.data
                iSharedPrefrance.saveToken(data.accessToken)
                iSharedPrefrance.saveRefreshToken(data.refreshToken)
                Resource.Success(AuthorizationData(data.accessToken, data.refreshToken))
            }
            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): Resource<AuthorizationData> {
        val result = apiCall {
            identityService.refreshToken(refreshTokenRequest)
        }
        return when (result) {
            is Resource.Success -> {
                val data = result.data
                iSharedPrefrance.saveToken(data.accessToken)
                iSharedPrefrance.saveRefreshToken(data.refreshToken)
                Resource.Success(AuthorizationData(data.accessToken, data.refreshToken))
            }
            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): Resource<Boolean> {
        val result = apiCall {
            identityService.changePassword(changePasswordRequest)
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(true)
            }
            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }
}