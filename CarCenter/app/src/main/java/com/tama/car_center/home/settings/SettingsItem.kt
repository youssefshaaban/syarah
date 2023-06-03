package com.tama.car_center.home.settings

import androidx.annotation.DrawableRes

data class SettingsItem(val name:String,@DrawableRes val idIcon:Int,val naveIdAction:Int?=null,val cls:Class<*>? =null)