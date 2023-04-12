package com.tama.syarah.change_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.syarah.R
import com.tama.syarah.databinding.ActivityChangePasswordBinding
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
                finish()
            }
        }
    }
}