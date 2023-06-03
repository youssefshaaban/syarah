package com.tama.domain.model

data class CarService(
    val address: String,
    val bankAccountNumber: String,
    val centerStatus: Int,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String
)