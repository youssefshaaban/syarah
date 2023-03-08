package com.tama.syarah.login

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tama.syarah.home.HomeActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    val finshiViewLiveData = MutableLiveData(false)

    val onClickLogin: (View) -> Unit = { view ->
        view.context.startActivity(Intent(view.context, HomeActivity::class.java))
        finshiViewLiveData.value = true
    }
}