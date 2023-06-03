package com.tama.data.remote

import com.tama.data.entity.LoginResponse
import com.tama.domain.entity.ChangePasswordRequest
import com.tama.domain.entity.RefreshTokenRequest
import com.tama.domain.entity.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IdentityService {

    @POST("Identity/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

     @POST("Identity/refresh-token")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Response<LoginResponse>

    @POST("Identity/change-password")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Response<LoginResponse>

}