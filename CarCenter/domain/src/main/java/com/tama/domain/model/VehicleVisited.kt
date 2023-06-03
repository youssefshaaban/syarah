package com.tama.domain.model

data class VehicleVisited(
    val vehicleOilType: String? = null,
    val vehiclePlateNumber: String? = null,
    val visitId: Int,
    var isOngoing: Boolean = false
)