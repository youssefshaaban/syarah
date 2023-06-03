package com.tama.domain.entity

data class RefreshTokenRequest(
    val accessToken: String,
    val refreshToken: String
)