package com.tama.domain.usecases.driver

import com.tama.domain.model.Driver
import com.tama.domain.repository.DriverRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class GetDriverInfoUseCases @Inject constructor(private val driverRepository: DriverRepository) {
    suspend operator fun invoke(): Resource<Driver> =
        driverRepository.getDriverInfo()
}