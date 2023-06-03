package com.tama.car_center.onboarding
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.car_center.login.LoginActivity
import com.tama.car_center.util.LocaleHelper
import com.tama.syarah.R
import com.tama.syarah.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding
    val viewModel: OnboardingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setLocale(viewModel.getLanguage)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        binding.also { it.viewModel = viewModel }
        viewModel.finshiViewLiveData.observe(this) { isFinish ->
            if (isFinish)
                finish()
        }
        viewModel.goLogin.observe(this) { isGologin ->
            if (isGologin == true) {
                startActivity(Intent(this, LoginActivity::class.java))
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