package com.tama.driver.login

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tama.domain.usecases.identity.LoginUseCases
import com.tama.domain.usecases.languague_uscase.GetLanguageUseCase
import com.tama.domain.util.Failure
import com.tama.driver.home.HomeActivity
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCases: LoginUseCases,getLanguageUseCase: GetLanguageUseCase) : ViewModel() {
    val finshiViewLiveData = MutableLiveData(false)
    val loadingVisible = MutableLiveData(false)
    val errorState = MutableLiveData<String>()
    private val email = MutableLiveData("")
    private val password = MutableLiveData("")
    val emailTextChanged: (CharSequence, Int, Int, Int) -> Unit = { text, start, before, count ->
        email.value = text.toString()
    }
    val passwordTextChanged: (CharSequence, Int, Int, Int) -> Unit = { text, start, before, count ->
        password.value = text.toString()
    }
    val onClickLogin: (View) -> Unit = { view ->
        viewModelScope.launch {
            loadingVisible.value = true
            Log.e("TAG", "onClickLogin: ${Thread.currentThread().name}")
            val dataResource = loginUseCases.invoke(
                email = email.value ?: "",
                password = password.value ?: ""
            )
            when (dataResource) {
                is com.tama.domain.util.Resource.Success -> {
                    loadingVisible.value = false
                    view.context.startActivity(Intent(view.context, HomeActivity::class.java))
                    finshiViewLiveData.value = true
                }
                is com.tama.domain.util.Resource.Error -> {
                    loadingVisible.value = false
                    when (dataResource.error) {
                        is Failure.NetworkConnection -> {
                            errorState.value = "No Internet Connection"
                        }
                        is Failure.ServerError -> {
                            errorState.value = "Server Error"
                        }
                        is Failure.UnknownError -> {
                            errorState.value = (dataResource.error as Failure.UnknownError).message
                                ?: "Unknown Error"
                        }
                        else -> {
                            errorState.value = "Something happen"
                        }
                    }
                }
            }
        }
    }
}