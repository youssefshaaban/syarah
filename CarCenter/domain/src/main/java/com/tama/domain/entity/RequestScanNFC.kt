package com.tama.domain.entity

data class RequestScanNFC(
    val driverCode: Int,
    val vehicleNfc: String
)