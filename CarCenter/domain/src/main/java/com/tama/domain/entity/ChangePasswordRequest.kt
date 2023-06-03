package com.tama.domain.entity

data class ChangePasswordRequest(
    val accountType: Int,
    val currentPassword: String,
    val newPassword: String
)