package com.tama.syarah.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

object UtilsBindAdapter {
    @JvmStatic
    @BindingAdapter("app:iconSrc")
    fun bindIMageResource(imageView: AppCompatImageView,iconRes:Int){
        imageView.setImageResource(iconRes)
    }
}