package com.tama.syarah.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tama.syarah.R
import com.tama.syarah.login.LoginActivity
import com.tama.syarah.onboarding.OnboardingActivity
import com.tama.syarah.onboarding.change_language.ChangeLanguageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
      //  splashScreen.setKeepOnScreenCondition { true }
        viewModel.initNextView()
        setupSplashScreen(splashScreen)
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.openLanguageSelection.observe(this) {
            startActivity(Intent(this, ChangeLanguageActivity::class.java))
            finish()
        }
        viewModel.openOnBoarding.observe(this) {
            if (it) {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }
        viewModel.openLogin.observe(this) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupSplashScreen(splashScreen: SplashScreen) {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.dataLoaded) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false
                }
            }
        )

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.view.width.toFloat()
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = 800L
                doOnEnd { splashScreenView.remove() }
            }

            slideBack.start()
        }
    }
}