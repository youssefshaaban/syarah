package com.tama.car_center.util

import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter

object UtilsBindAdapter {
    @JvmStatic
    @BindingAdapter("app:iconSrc")
    fun bindIMageResource(imageView: AppCompatImageView,iconRes:Int){
        imageView.setImageResource(iconRes)
    }
    @JvmStatic
    @BindingAdapter("app:TextChange",requireAll = false)
    fun bindIMageResource(editText: AppCompatEditText,textChange:((CharSequence?,Int,Int,Int)->Unit)?=null){
        textChange?.let {  editText.doOnTextChanged(textChange)}
    }
}