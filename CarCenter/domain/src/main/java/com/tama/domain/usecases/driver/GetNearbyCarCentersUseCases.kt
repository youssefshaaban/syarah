package com.tama.domain.usecases.driver

import com.tama.domain.entity.NearbyParams
import com.tama.domain.model.CarService
import com.tama.domain.repositories.WorkerRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class GetNearbyCarCentersUseCases @Inject constructor(private val driverRepository: WorkerRepository) {
    suspend operator fun invoke(nearbyParams: NearbyParams): Resource<List<CarService>> =
        driverRepository.getNearbyCenters(nearbyParams)
}