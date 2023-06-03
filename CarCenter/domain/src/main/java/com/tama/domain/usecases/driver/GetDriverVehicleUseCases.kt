package com.tama.domain.usecases.driver

import com.tama.domain.model.Vechical
import com.tama.domain.repositories.WorkerRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class GetDriverVehicleUseCases @Inject constructor(private val driverRepository: WorkerRepository) {
    suspend operator fun invoke(): Resource<Vechical> =
        driverRepository.getDriverVechical()
}