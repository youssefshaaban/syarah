package com.tama.syarah.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.syarah.R
import com.tama.syarah.change_language.ChangeLanguageActivity
import com.tama.syarah.databinding.ActivitySplashScreenBinding
import com.tama.syarah.login.LoginActivity
import com.tama.syarah.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    val viewModel:SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)
        viewModel.openLanguageSelection.observe(this){
            startActivity(Intent(this,ChangeLanguageActivity::class.java))
            finish()
        }
        viewModel.openOnBoarding.observe(this){
            if (it){
                startActivity(Intent(this,OnboardingActivity::class.java))
                finish()
            }
        }
        viewModel.openLogin.observe(this){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}