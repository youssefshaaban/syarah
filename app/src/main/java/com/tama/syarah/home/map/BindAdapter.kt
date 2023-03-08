package com.tama.syarah.home.map

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.tama.domain.model.CarService
import com.tama.syarah.R

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



