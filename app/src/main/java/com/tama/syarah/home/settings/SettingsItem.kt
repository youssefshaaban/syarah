package com.tama.syarah.home.settings

import androidx.annotation.DrawableRes

data class SettingsItem(val name:String,@DrawableRes val idIcon:Int,val naveIdAction:Int?=null)