package com.tama.syarah.account_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tama.syarah.R
import com.tama.syarah.change_password.ChangePasswordActivity
import com.tama.syarah.databinding.ActivityAccountInformationBinding

class AccountInformationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAccountInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_account_information)
        binding.icBack.setOnClickListener { finish() }
        binding.changePassword.setOnClickListener { startActivity(Intent(this,ChangePasswordActivity::class.java))  }
    }
}