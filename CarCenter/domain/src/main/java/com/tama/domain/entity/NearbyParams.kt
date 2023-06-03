package com.tama.domain.entity

data class NearbyParams(val Latitude: Double?=null,val  Longitude: Double?=null, val Distance: Int?=null,val SearchKey:String?=null)
fun NearbyParams.toMap(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    Latitude?.let { map["Lat"] = it.toString() }
    Longitude?.let { map["Lng"] = it.toString() }
    Distance?.let { map["Distance"] = it.toString() }
    SearchKey?.let { map["SearchKey"] = it }
    return map
}