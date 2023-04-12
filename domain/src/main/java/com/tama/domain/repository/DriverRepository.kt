package com.tama.domain.repository

import com.tama.domain.entity.NearbyParams
import com.tama.domain.model.CarService
import com.tama.domain.model.Driver
import com.tama.domain.model.Vechical
import com.tama.domain.util.Resource

interface DriverRepository {
    suspend fun getNearbyCenters(nearbyParams: NearbyParams): Resource<List<CarService>>
    suspend fun getDriverInfo(): Resource<Driver>
    suspend fun getDriverVechical(): Resource<Vechical>
}