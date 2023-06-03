package com.tama.domain.model

data class Service(
    val amount: Int,
    val id: Int,
    val images: List<String>,
    val serviceType: Int,
    val vehicleKilometers: Int
)