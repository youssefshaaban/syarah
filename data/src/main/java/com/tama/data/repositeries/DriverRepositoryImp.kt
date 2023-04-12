package com.tama.data.repositeries

import com.tama.data.remote.DriverApi
import com.tama.data.util.apiCall
import com.tama.domain.entity.*
import com.tama.domain.model.CarService
import com.tama.domain.model.Driver
import com.tama.domain.model.Vechical
import com.tama.domain.repository.DriverRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class DriverRepositoryImp @Inject constructor(
    private val driverApi: DriverApi,
) : DriverRepository {

    override suspend fun getNearbyCenters(nearbyParams: NearbyParams): Resource<List<CarService>> {
        val result = apiCall {
            driverApi.getDriverNearbyCenters(nearbyParams.toMap())
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(result.data)
            }
            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun getDriverInfo(): Resource<Driver> {
        val result = apiCall {
            driverApi.getDriverInfo()
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(result.data)
            }
            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun getDriverVechical(): Resource<Vechical> {
        val result = apiCall {
            driverApi.getDriverVehicle()
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(result.data)
            }
            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }
}