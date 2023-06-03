package com.tama.driver.home.map

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap

import com.tama.domain.model.CarService
import com.tama.driver.R
import com.tama.driver.databinding.FragmentMapBinding
import com.tama.driver.util.LocationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*


@AndroidEntryPoint
class MapFragment : Fragment(), LocationHelper.LocationCallback {

    private var _binding: FragmentMapBinding? = null
    private var googleMap: GoogleMap? = null
    private var isGMSSupported = true
    var locationHelper: LocationHelper? = null

    companion object {
        const val CAR_MAP_PERMISSION_REQUEST_CODE = 45
        const val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MapViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        _binding.also { it?.viewModel = viewModel }
        _binding?.lifecycleOwner = this
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            MapsInitializer.initialize(view.context)
        } catch (e: GooglePlayServicesNotAvailableException) {
            isGMSSupported = false
        }
        lifecycleScope.launchWhenCreated {
            binding.searchCustom.getQueryTextChangeStateFlow().debounce(500)
                .filter { it->it.isNotEmpty() }
                .distinctUntilChanged().collectLatest {
                    viewModel.search(it)
                }
        }

        lifecycleScope.launchWhenCreated {
            val mapFragment: SupportMapFragment? =
                childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            val googleMap: GoogleMap? = mapFragment?.awaitMap()
            this@MapFragment.googleMap = googleMap
            googleMap?.let { viewModel.onMapReady { googleMap } }
        }
        viewModel.moveCameraToCurrentSelectedCarService.observe(viewLifecycleOwner) {
            it?.let { store ->
                googleMap?.moveCamera(store)
            }
        }

        viewModel.checkLocationPermission.observe(viewLifecycleOwner) { event ->
            event?.also {
                if (it) {
                    if (checkLocationPermission(view.context).not()) {
                        requestPermission()
                    } else {
                        viewModel.onCheckPermissionCompleted(true)
                    }
                }
            }
        }

        viewModel.showLocationButton.observe(viewLifecycleOwner) { event ->
            event.also {
                if (it) {
                    enableLocationButton(view.context)
                    getCurrentLocationFromDevice(view.context)
                }
            }
        }

        viewModel.addCarServiceCircles.observe(viewLifecycleOwner) { event ->
            googleMap?.apply {
                event?.let { list ->
                    list.onEach { carService ->
                        addDot(carService.latitude, carService.longitude)
                    }
                    setOnMarkerClickListener {
                        viewModel.onCircleDotClicked(it.position.latitude, it.position.longitude)
                        false
                    }
                }
            }
        }
    }


    private fun GoogleMap.addDot(latitude: Double, longitude: Double) {
        addMarker(
            MarkerOptions()
                .position(LatLng(latitude, longitude))
                .icon(
                    requireContext().getBitmapFromVector(R.drawable.ic_map_pin)?.let {
                        BitmapDescriptorFactory.fromBitmap(
                            it
                        )
                    })
        )
    }

    private fun GoogleMap.moveCamera(it: CarService) {
        val location = LatLng(it.latitude, it.longitude)
        val shouldMoveCamera = viewModel.currentSelectedStoreMarker?.let { marker ->
            marker.remove()
            false
        } ?: true
        viewModel.currentSelectedStoreMarker = addMarker(
            MarkerOptions()
                .position(location)
                .icon(
                    requireContext().getBitmapFromVector(R.drawable.ic_map_pin)?.let { it1 ->
                        BitmapDescriptorFactory.fromBitmap(
                            it1
                        )
                    })
        )
        val cameraUpdate =
            CameraUpdateFactory.newLatLngZoom(location, viewModel.animationCameraZoom)
        if (shouldMoveCamera) {
            moveCamera(cameraUpdate)
        } else {
            animateCamera(cameraUpdate)
        }
    }

    //
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    private fun enableLocationButton(context: Context) {
        if (checkLocationPermission(context)) {
            googleMap?.isMyLocationEnabled = true
        }
    }

    private fun getCurrentLocationFromDevice(context: Context) {
        locationHelper = LocationHelper(context, this)
        locationHelper?.requestLocationUpdates()
    }


    private fun checkLocationPermission(context: Context) =
        isPermissionGranted(context, Manifest.permission.ACCESS_FINE_LOCATION) &&
                isPermissionGranted(context, Manifest.permission.ACCESS_COARSE_LOCATION)


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun isPermissionGranted(context: Context, permission: String) =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    private fun Context.getBitmapFromVector(vectorResId: Int): Bitmap? =
        AppCompatResources.getDrawable(this, vectorResId)?.toBitmap()


    private fun requestPermission() {
        if (shouldShowRequestPermissionRationale(locationPermission)) {
            // Explain why the permission is needed
            AlertDialog.Builder(requireContext())
                .setTitle("Location Permission")
                .setMessage("We need your location to show you nearby places")
                .setPositiveButton("OK") { _, _ ->
                    requestPermissionLauncher.launch(locationPermission)
                }
                .setNegativeButton("Cancel", null)
                .show()
        } else {
            // Permission has not been requested yet, request it
            requestPermissionLauncher.launch(locationPermission)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.onCheckPermissionCompleted(isGranted)
                activity?.let { getCurrentLocationFromDevice(it) }

            } else {
                // Permission has been denied, show an error message
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onLocationReceived(location: Location) {
        Log.d("location", location.toString())
        locationHelper?.removeLocationUpdates()
        viewModel.onLocationReceived(location)
    }
}