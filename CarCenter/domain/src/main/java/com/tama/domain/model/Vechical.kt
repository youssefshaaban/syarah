package com.tama.domain.model

data class Vechical(
    val branchName: String? = null,
    val nextOilChange: String? = null,
    val nfcCode: String? = null,
    val oilBrand: String? = null,
    val oilChangeKilometers: Int? = null,
    val oilType: String? = null,
    val oilViscosity: String? = null,
    val plateNumber: String? = null,
    val vehicleId: Int? = null,
    val vehicleModel: String? = null,
    val vehicleType: Int? = null
)