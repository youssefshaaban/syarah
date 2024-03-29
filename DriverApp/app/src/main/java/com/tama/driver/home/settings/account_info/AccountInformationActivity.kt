package com.tama.driver.home.settings.account_info

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.driver.R
import com.tama.driver.databinding.ActivityAccountInformationBinding
import com.tama.driver.home.settings.change_password.ChangePasswordActivity
import com.tama.driver.util.LocaleHelper
import com.tama.driver.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountInformationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAccountInformationBinding
    private val viewModel: AccountInformationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_information)
        binding.icBack.setOnClickListener { finish() }
        binding.changePassword.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ChangePasswordActivity::class.java
                )
            )
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.errorState.observe(this) {
            if (it.isNotEmpty()) {
                binding.changePassword.showToast(it, Toast.LENGTH_SHORT)       //show error
            }
        }

    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(LocaleHelper.onAttach(newBase))
        }
    }
}