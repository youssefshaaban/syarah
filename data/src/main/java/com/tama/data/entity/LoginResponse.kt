package com.tama.data.entity

data class LoginResponse(
    val refreshToken: String,
    val accessToken: String
)
