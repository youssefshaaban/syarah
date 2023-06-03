package com.tama.data.entity

import com.tama.domain.model.Vechical

data class Vehicle(
    val branchName: String,
    val oilBrand: String,
    val oilViscosity: String,
    val plateNumber: String,
    val vehicleModel: String,
    val vehicleType: Int
)


fun Vehicle.toVechicalModel(): Vechical {
    return Vechical(
        branchName = this.branchName,
        oilViscosity = this.oilViscosity,
        oilBrand = this.oilBrand,
        vehicleType = this.vehicleType,
        vehicleModel = this.vehicleModel,
        plateNumber = this.plateNumber,

    )
}