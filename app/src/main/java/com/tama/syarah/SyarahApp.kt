package com.tama.syarah

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.tama.domain.usecases.languague_uscase.GetLanguageUseCase
import com.tama.syarah.util.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject


@HiltAndroidApp
class SyarahApp:Application(){
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