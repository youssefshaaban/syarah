package com.tama.syarah.home.map


import androidx.lifecycle.ViewModel
import com.tama.domain.model.CarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {

 val list= listOf(CarService(1),CarService(1),CarService(1),CarService(1))

}