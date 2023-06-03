package com.tama.domain.entity

import java.io.File

class RequestServiceToVechical(
    val AvailabilityId: Int,
    val Amount: Int,
    val VehicleKilometers: Int,
    val images: Array<File>? = null,
    val visitId: Int? = null
)