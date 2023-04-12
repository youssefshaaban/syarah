package com.tama.domain.entity

data class LoginRequest(
    val accountType: Int,
    val email: String,
    val password: String
)