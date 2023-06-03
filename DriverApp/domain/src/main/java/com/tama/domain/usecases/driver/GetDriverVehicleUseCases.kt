package com.tama.domain.usecases.driver

import com.tama.domain.model.Vechical
import com.tama.domain.repositories.DriverRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class GetDriverVehicleUseCases @Inject constructor(private val driverRepository: DriverRepository) {
    suspend operator fun invoke(): Resource<Vechical> =
        driverRepository.getDriverVechical()
}