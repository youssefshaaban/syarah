package com.tama.car_center.home.settings.change_language

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.tama.car_center.home.HomeActivity
import com.tama.car_center.util.LocaleHelper
import com.tama.syarah.R
import com.tama.syarah.databinding.ActivityChangeLanguage2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeLanguageSettingActivity : AppCompatActivity() {
    val viewModel: ChangeLanViewModel by viewModels()
    lateinit var binding: ActivityChangeLanguage2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_language2)
        binding.also { it.viewModel = viewModel }
        binding.lifecycleOwner = this
        binding.icBack.setOnClickListener { finish() }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.shouldShowAlertChangeLanguage.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    showAlertChangeLanguageDialog()
                }
            }
        }
    }

    private fun showAlertChangeLanguageDialog() {
        AlertDialog.Builder(this).setMessage(getString(R.string.txt_message_change_language))
            .setNegativeButton(
                getString(R.string.txt_ok)
            ) { dialog, which ->
                viewModel.saveValue()
                dialog.dismiss()
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    ).apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK) })
                this@ChangeLanguageSettingActivity.finishAffinity()
            }
            .setPositiveButton(
                getString(R.string.txt_cancel)
            ) { dialog, which ->
                dialog.dismiss()
            }.show()
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(LocaleHelper.onAttach(newBase))
        }
    }

}