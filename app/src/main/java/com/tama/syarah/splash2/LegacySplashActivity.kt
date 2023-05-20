package com.tama.syarah.splash2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tama.syarah.home.HomeActivity
import com.tama.syarah.login.LoginActivity
import com.tama.syarah.onboarding.OnboardingActivity
import com.tama.syarah.onboarding.change_language.ChangeLanguageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LegacySplashActivity : AppCompatActivity() {
    val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        viewModel.initNextView()
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.openLanguageSelection.observe(this) {event->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    startActivity(Intent(this, ChangeLanguageActivity::class.java))
                    finish()
                }
            }
        }
        viewModel.openOnBoarding.observe(this) {event->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    startActivity(Intent(this, OnboardingActivity::class.java))
                    finish()
                }
            }
        }
        viewModel.openLogin.observe(this) {event->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
        viewModel.openHome.observe(this) {event->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
        }
    }
}