package com.tama.driver.home.map


import android.location.Location
import androidx.lifecycle.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.tama.domain.entity.NearbyParams
import com.tama.domain.model.CarService
import com.tama.domain.usecases.driver.GetNearbyCarCentersUseCases
import com.tama.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val nearbyCarCentersUseCases: GetNearbyCarCentersUseCases) :
    ViewModel() {

    val carServices = MutableLiveData<List<CarService>>()
    val googleMapLiveData = MutableLiveData<() -> GoogleMap>()
    var currentSelectedStoreMarker: Marker? = null
    val checkLocationPermission = MediatorLiveData<Boolean>()
    val showLocationButton = MutableLiveData<Boolean>()
    val loadingVisiblity = MutableLiveData(false)
    val currentLocation = MutableLiveData<Location>()
    val addCarServiceCircles = MediatorLiveData<List<CarService>>().apply {
        addSource(carServices) {
            if (isMapReady()) {
                value = it
            }
        }
        addSource(googleMapLiveData) {
            carServices.value?.let {
                if (isMapReady()) {
                    value = it
                }
            }
        }
    }.distinctUntilChanged()
    private val currentSelectedCarService = MutableLiveData<CarService>()
    val animationCameraZoom = 18F

    val moveCameraToCurrentSelectedCarService = MediatorLiveData<CarService>().apply {
        addSource(currentSelectedCarService) {
            if (isMapReady()) {
                value = it
            }
        }
        addSource(googleMapLiveData) {
            currentSelectedCarService.value?.let {
                if (isMapReady()) {
                    value = it
                }
            }
        }
    }.distinctUntilChanged()
    val currentSelectedStorePosition = MediatorLiveData<Int>().apply {
        addSource(currentSelectedCarService) {
            it?.let {
                value = carServices.value?.indexOf(it)
            }
        }
    }.distinctUntilChanged()

    private fun isMapReady() = googleMapLiveData.value != null

    fun onMapReady(googleMapGetter: () -> GoogleMap) {
        googleMapLiveData.value = googleMapGetter
        checkLocationPermission.value = true
    }

    fun onCheckPermissionCompleted(isGranted: Boolean) {
        if (isGranted) {
            showLocationButton.value = true
        }
    }

    fun onCircleDotClicked(latitude: Double, longitude: Double) {
        currentSelectedCarService.value = carServices.value?.find {
            it.latitude == latitude && it.longitude == longitude
        }
    }

    val onStoreSelectedAction: (position: Int) -> Unit = {
        currentSelectedCarService.value = carServices.value?.get(it)
    }


    fun getNearByCarCenter(NearbyParams: NearbyParams) {
        loadingVisiblity.value = true
        viewModelScope.launch {
            val result = nearbyCarCentersUseCases(NearbyParams)
            delay(1000)
            if (result is Resource.Success) {
                loadingVisiblity.value = false
                carServices.value = result.data
            } else {
                carServices.value = emptyList()
                loadingVisiblity.value = false
            }
        }
    }

    fun search(search:String){
        getNearByCarCenter(NearbyParams(currentLocation.value?.latitude, currentLocation.value?.longitude,Distance = 8, SearchKey = search))
    }

    fun onLocationReceived(location: Location) {
        currentLocation.value = location
        getNearByCarCenter(NearbyParams(location.latitude, location.longitude))
    }
}