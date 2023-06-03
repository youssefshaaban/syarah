package com.tama.domain.repositories

import com.tama.domain.entity.NearbyParams
import com.tama.domain.entity.RequestServiceToVechical
import com.tama.domain.entity.RequestScanNFC
import com.tama.domain.model.CarService
import com.tama.domain.model.Driver
import com.tama.domain.model.ServiceCategory
import com.tama.domain.model.ServiceTypeCategory
import com.tama.domain.model.Vechical
import com.tama.domain.model.VehicalInformation
import com.tama.domain.model.VehicleVisited
import com.tama.domain.util.Resource

interface WorkerRepository {
    suspend fun scanCode(requestScanNFC: RequestScanNFC): Resource<Boolean>
    suspend fun getVehicalScanedByWorker(ongoing: Boolean): Resource<List<VehicleVisited>>
    suspend fun getDetailForVisit(visitId: Int):Resource<VehicalInformation>
    suspend fun getServiceTpeForCar(visitId: Int,categoryId:Int):Resource<List<ServiceTypeCategory>>
    suspend fun getServiceCategory(): Resource<List<ServiceCategory>>
    suspend fun addServiceForSpecific(addServiceToVechical: RequestServiceToVechical): Resource<Boolean>
    suspend fun updateServiceForSpecific(addServiceToVechical: RequestServiceToVechical): Resource<Boolean>
    suspend fun deleteServiceFromVisit(serviceId: Int, visitId: Int): Resource<Boolean>

}