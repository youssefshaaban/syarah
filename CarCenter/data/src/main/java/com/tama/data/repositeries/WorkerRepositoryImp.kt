package com.tama.data.repositeries

import com.tama.data.entity.toVehicalInformation
import com.tama.data.remote.WorkerApi
import com.tama.data.util.apiCall
import com.tama.domain.entity.*
import com.tama.domain.model.ServiceCategory
import com.tama.domain.model.ServiceTypeCategory
import com.tama.domain.model.VehicalInformation
import com.tama.domain.model.VehicleVisited
import com.tama.domain.repositories.WorkerRepository
import com.tama.domain.util.Resource
import javax.inject.Inject

class WorkerRepositoryImp @Inject constructor(
    private val driverApi: WorkerApi,
) : WorkerRepository {
    override suspend fun scanCode(requestScanNFC: RequestScanNFC): Resource<Boolean> {
        val result = apiCall {
            driverApi.registerVehicle(requestScanNFC)
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(true)
            }

            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun getVehicalScanedByWorker(ongoing: Boolean): Resource<List<VehicleVisited>> {
        val result = apiCall {
            driverApi.getVehcailsScanned(ongoing.toString())
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

    override suspend fun getDetailForVisit(visitId: Int): Resource<VehicalInformation> {
        val result = apiCall {
            driverApi.getVehicleByVisit(visitId)
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(result.data.toVehicalInformation())
            }

            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun getServiceTpeForCar(
        visitId: Int,
        categoryId: Int
    ): Resource<List<ServiceTypeCategory>> {
        val result = apiCall {
            driverApi.getServiceByCategoryAndVisit(visitId, categoryId)
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

    override suspend fun getServiceCategory(): Resource<List<ServiceCategory>> {
        val result = apiCall {
            driverApi.getCategoryService()
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

    override suspend fun addServiceForSpecific(addServiceToVechical: RequestServiceToVechical): Resource<Boolean> {
        val result = apiCall {
            driverApi.addService(
                addServiceToVechical.visitId ?: 0,
                requestServiceToVechical = addServiceToVechical
            )
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(true)
            }

            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun updateServiceForSpecific(addServiceToVechical: RequestServiceToVechical): Resource<Boolean> {
        val result = apiCall {
            driverApi.updateService(
                addServiceToVechical.visitId ?: 0,
                requestServiceToVechical = addServiceToVechical
            )
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(true)
            }

            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

    override suspend fun deleteServiceFromVisit(serviceId: Int, visitId: Int): Resource<Boolean> {
        val result = apiCall {
            driverApi.deleteService(
                visitId = visitId,
                serviceId = serviceId
            )
        }
        return when (result) {
            is Resource.Success -> {
                Resource.Success(true)
            }

            is Resource.Error -> {
                Resource.Error(result.error)
            }
        }
    }

}