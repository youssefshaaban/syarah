package com.tama.syarah.home.settings.change_language

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.syarah.R
import com.tama.syarah.databinding.ActivityChangeLanguage2Binding
import com.tama.syarah.splash.SplashScreenActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeLanguageSettingActivity : AppCompatActivity() {
    val viewModel: ChangeLanViewModel by viewModels()
    lateinit var binding: ActivityChangeLanguage2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_language2)
        binding.also { it.viewModel = viewModel }
        binding.lifecycleOwner = this
        viewModel.shouldFinishActiivty.observe(this) {
            if (it) {
                startActivity(
                    Intent(
                        this,
                        SplashScreenActivity::class.java
                    ).apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK) })
                finishAffinity()
            }
        }
    }
}