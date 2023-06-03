package com.tama.driver.home.vehicle


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tama.domain.model.Vechical
import com.tama.domain.usecases.driver.GetDriverVehicleUseCases
import com.tama.domain.util.Failure
import com.tama.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    val getDriverVehicleUseCases: GetDriverVehicleUseCases
) : ViewModel() {

    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData(true)
    val errorState: MutableLiveData<String> = MutableLiveData()
    val vechical = MutableLiveData<Vechical>()

    init {
        if (vechical.value == null) {
            loadData()
        } else {
            loadingVisibility.value = false
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val result = getDriverVehicleUseCases.invoke()
            delay(2000)
            if (result is Resource.Success) {
                loadingVisibility.value = false
                vechical.value = result.data
            } else if (result is Resource.Error) {
                loadingVisibility.value = false
                when (result.error) {
                    is Failure.NetworkConnection -> {
                        errorState.value = "No Internet Connection"
                    }
                    is Failure.ServerError -> {
                        errorState.value = "Server Error"
                    }
                    is Failure.UnknownError -> {
                        errorState.value = (result.error as Failure.UnknownError).message
                            ?: "Unknown Error"
                    }
                    else -> {
                        errorState.value = "Something happen"
                    }
                }
            }
        }

    }
}

