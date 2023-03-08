package com.tama.syarah.change_language

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.syarah.R
import com.tama.syarah.databinding.ActivityChangeLanguageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeLanguageActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangeLanguageBinding
    val viewmodel: ChangeLanguageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_language)
        binding.also { it.viewModel = viewmodel }
    }
}