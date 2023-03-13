package com.tama.syarah

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import java.util.*


@HiltAndroidApp
class SyarahApp:Application(){
    override fun onCreate() {
        super.onCreate()
        Log.e("language",Locale.getDefault().language)
    }
}