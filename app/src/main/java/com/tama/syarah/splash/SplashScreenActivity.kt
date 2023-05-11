package com.tama.syarah.splash


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.tama.syarah.R
import com.tama.syarah.onboarding.change_language.ChangeLanguageActivity
import com.tama.syarah.login.LoginActivity
import com.tama.syarah.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    val viewModel:SplashViewModel by viewModels()
    lateinit var content:View
    override fun onCreate(savedInstanceState: Bundle?) {
      //  val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legacy_splash)
       // splashScreen.setKeepOnScreenCondition { true }
        // For this example, the Timer delay represents awaiting the data to determine where to navigate
//        Timer().schedule(1500){
//            viewModel.initNextView()
//        }
        content = findViewById(android.R.id.content)
        customSplashInit()
        viewModel.openLanguageSelection.observe(this){
            startActivity(Intent(this, ChangeLanguageActivity::class.java))
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

    private fun customSplashInit() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            Log.d("MainActivity", "onCreate: I AM RUNNING ON API 12 or higher")
//            content.viewTreeObserver.addOnPreDrawListener(object :
//                ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean =
//                    when {
//                        viewModel.initNextView() -> {
//                            content.viewTreeObserver.removeOnPreDrawListener(this)
//                            true
//                        }
//                        else -> false
//                    }
//            })
//
//            // custom exit on splashScreen
//            splashScreen.setOnExitAnimationListener { splashScreenView ->
//                // custom animation.
//                ObjectAnimator.ofFloat(
//                    splashScreenView,
//                    View.TRANSLATION_X,
//                    0f,
//                    splashScreenView.width.toFloat()
//                ).apply {
//                    duration = 1500
//                    // Call SplashScreenView.remove at the end of your custom animation.
//                    doOnEnd {
//                        splashScreenView.remove()
//                    }
//                }.also {
//                    // Run your animation.
//                    it.start()
//                }
//            }
//        }
    }
}