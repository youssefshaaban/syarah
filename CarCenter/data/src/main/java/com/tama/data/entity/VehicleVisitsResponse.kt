package com.tama.data.entity

import com.tama.domain.model.Service
import com.tama.domain.model.VehicalInformation

data class VehicleVisitsResponse(
    val services: List<Service>,
    val vehicle: Vehicle
)

fun VehicleVisitsResponse.toVehicalInformation():VehicalInformation{
    return VehicalInformation(services = services,vehicle=this.vehicle.toVechicalModel())
}