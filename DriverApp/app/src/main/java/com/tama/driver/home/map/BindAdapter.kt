package com.tama.driver.home.map

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.tama.domain.model.CarService
import com.tama.driver.R

@BindingAdapter("app:carServiceList", requireAll = true)
internal fun setCarList(
    viewPager2: ViewPager2,
    list: List<CarService>?,
) {
    if (list != null) {
        if (viewPager2.adapter == null) {
            val carService = CarServiceViewPagerAdapter(null)
            carService.submitList(list)
            viewPager2.adapter = carService
            viewPager2.offscreenPageLimit = 1
            viewPager2.setupDecoration()
        } else {
            val adapter = viewPager2.adapter as CarServiceViewPagerAdapter
            adapter.submitList(list)
        }
    }
}
@BindingAdapter("app:setupStoreGoogleMap")
internal fun setupStoreGoogleMap(mapView: MapView, googleMapGetter: (() -> GoogleMap)?) {
    val zoom = 18F
    googleMapGetter?.invoke()?.let {
        it.isBuildingsEnabled = false
        it.isTrafficEnabled = false
        it.isIndoorEnabled = false
        it.uiSettings.isIndoorLevelPickerEnabled = false
        it.uiSettings.isMyLocationButtonEnabled = true
        it.moveCamera(CameraUpdateFactory.zoomTo(zoom))
    }
}

private fun ViewPager2.setupDecoration() {
    val nextItemVisiblePx = context.resources.getDimension(R.dimen.dimen_16dp)
    val currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.dimen_16dp)
    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
    setPageTransformer { page, position ->
        page.translationX = -pageTranslationX * position
    }
    addItemDecoration(HorizontalItemDecoration(context,
        R.dimen.dimen_16dp))
}



