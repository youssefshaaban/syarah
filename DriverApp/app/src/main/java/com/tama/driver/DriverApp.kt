package com.tama.driver

import android.app.Application
import android.content.Context
import android.util.Log
import com.tama.domain.usecases.languague_uscase.GetLanguageUseCase
import com.tama.driver.util.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject


@HiltAndroidApp
class DriverApp:Application(){
    @Inject
    lateinit var getLanguageUseCase: GetLanguageUseCase
    override fun onCreate() {
        super.onCreate()
        Log.e("language",Locale.getDefault().language)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base!!, "en"))
    }
}