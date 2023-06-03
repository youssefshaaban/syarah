package com.tama.driver.home.settings.change_password

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.driver.R
import com.tama.driver.databinding.ActivityChangePasswordBinding
import com.tama.driver.util.LocaleHelper
import com.tama.driver.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangePasswordBinding
    val viewModel: ChangePasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        binding.also { it.viewModel = viewModel }
        binding.lifecycleOwner = this
        binding.icBack.setOnClickListener { finish() }

        viewModel.onSucces.observe(this) {
            if (it) {
                binding.saveChange.showToast(getString(R.string.save_succefully), Toast.LENGTH_SHORT)
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