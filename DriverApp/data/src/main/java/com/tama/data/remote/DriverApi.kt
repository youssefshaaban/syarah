package com.tama.data.remote

import com.tama.domain.model.CarService
import com.tama.domain.model.Driver
import com.tama.domain.model.Vechical
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface DriverApi {
    @GET("app/Driver/driver-info")
    suspend fun getDriverInfo():Response<Driver>
    @GET("app/Driver/nearby-centers")
    suspend fun getDriverNearbyCenters(@QueryMap map: Map<String,String>): Response<List<CarService>>

    @GET("app/Driver/vehicle")
    suspend fun getDriverVehicle():Response<Vechical>
}