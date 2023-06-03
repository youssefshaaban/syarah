package com.tama.driver.onboarding.change_language

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.driver.R
import com.tama.driver.databinding.ActivityChangeLanguageBinding
import com.tama.driver.util.LocaleHelper
import com.tama.driver.util.setLocale

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChangeLanguageActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangeLanguageBinding
    val viewModel: ChangeLanguageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale(viewModel.language)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_language)
        binding.lifecycleOwner = this
        binding.also { it.viewModel = viewModel }
        viewModel.shouldFinishActiivty.observe(this) {
            if (it) {
                finish()
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(LocaleHelper.onAttach(newBase))
        }
    }
}