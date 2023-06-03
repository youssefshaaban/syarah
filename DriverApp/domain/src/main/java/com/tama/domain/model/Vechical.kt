package com.tama.domain.model

data class Vechical(
    val branchName: String,
    val nextOilChange: String,
    val nfcCode: String,
    val oilBrand: String,
    val oilChangeKilometers: Int,
    val oilType: String,
    val oilViscosity: String,
    val plateNumber: String,
    val vehicleId: Int,
    val vehicleModel: String,
    val vehicleType: String
)