package com.tama.domain.usecases.driver

import com.tama.domain.entity.NearbyParams
import com.tama.domain.model.CarService
import com.tama.domain.repository.DriverRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class GetNearbyCarCentersUseCases @Inject constructor(private val driverRepository: DriverRepository) {
    suspend operator fun invoke(nearbyParams: NearbyParams): Resource<List<CarService>> =
        driverRepository.getNearbyCenters(nearbyParams)
}