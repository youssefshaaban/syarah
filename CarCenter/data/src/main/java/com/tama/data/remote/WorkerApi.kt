package com.tama.data.remote

import com.tama.data.entity.VehicleVisitsResponse
import com.tama.domain.entity.RequestScanNFC
import com.tama.domain.entity.RequestServiceToVechical
import com.tama.domain.model.CarService
import com.tama.domain.model.Driver
import com.tama.domain.model.ServiceCategory
import com.tama.domain.model.ServiceTypeCategory
import com.tama.domain.model.Vechical
import com.tama.domain.model.VehicleVisited
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface WorkerApi {
    @GET("app/worker/visits")
    suspend fun getVehcailsScanned(@Query("ongoing") ongoing: String): Response<List<VehicleVisited>>

    @POST("app/worker/scan")
    suspend fun registerVehicle(@Body requestScanNFC: RequestScanNFC): Response<ResponseBody>

    @GET("app/worker/visits/{visitId}")
    suspend fun getVehicleByVisit(@Path("visitId") visitId: Int): Response<VehicleVisitsResponse>

    @GET("app/worker/service-categories")
    suspend fun getCategoryService(): Response<List<ServiceCategory>>

    @GET("app/worker/services/{visitId}")
    suspend fun getServiceByCategoryAndVisit(
        @Path("visitId") visitId: Int,
        @Query("categoryId") categoryId: Int
    ): Response<List<ServiceTypeCategory>>


    @POST("app/worker/services/{visitId}")
    suspend fun addService(
        @Path("visitId") visitId: Int,
        @Body requestServiceToVechical: RequestServiceToVechical
    ): Response<ResponseBody>


    @PUT("app/worker/services/{visitId}")
    suspend fun updateService(
        @Path("visitId") visitId: Int,
        @Body requestServiceToVechical: RequestServiceToVechical
    ): Response<ResponseBody>


    @DELETE("app/worker/services/{visitId}")
    suspend fun deleteService(
        @Path("visitId") visitId: Int,
        @Query("serviceId") serviceId: Int
    ): Response<ResponseBody>


}