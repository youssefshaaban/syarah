package com.tama.domain.entity

data class AuthorizationData(
    val accessToken: String,
    val refreshToken: String
)