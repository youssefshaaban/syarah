package com.tama.syarah.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.tama.syarah.R
import com.tama.syarah.change_language.ChangeLanguageActivity
import com.tama.syarah.login.LoginActivity
import com.tama.syarah.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    val viewModel:SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
      //  val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legacy_splash)
       // splashScreen.setKeepOnScreenCondition { true }
        // For this example, the Timer delay represents awaiting the data to determine where to navigate
        Timer().schedule(1500){
            viewModel.initNextView()
        }
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