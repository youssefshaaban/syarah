package com.tama.driver.home.settings.change_password


import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tama.domain.usecases.identity.ChangePasswordUseCases
import com.tama.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(changePasswordUseCases: ChangePasswordUseCases) :
    ViewModel() {
    val loadingVisibility = MutableLiveData<Boolean>()
    val currentPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val retypePassword = MutableLiveData<String>()
    val onSucces = MutableLiveData<Boolean>()
    val currentPasswordTextChanged: (CharSequence, Int, Int, Int) -> Unit =
        { text, start, before, count ->
            currentPassword.value = text.toString()
        }
    val newPasswordTextChanged: (CharSequence, Int, Int, Int) -> Unit =
        { text, start, before, count -> newPassword.value = text.toString() }
    val retypePasswordTextChanged: (CharSequence, Int, Int, Int) -> Unit =
        { text, start, before, count -> retypePassword.value = text.toString() }

    val onClickSaveChange: (View) -> Unit = clickLstner@{ view ->
        if (currentPassword.value.isNullOrEmpty() || newPassword.value.isNullOrEmpty() || retypePassword.value.isNullOrEmpty()) {
            return@clickLstner
        }
        if (newPassword.value != retypePassword.value) {
            return@clickLstner
        }
        loadingVisibility.value = true
        viewModelScope.launch {
            when (
                changePasswordUseCases.invoke(
                    currentPassword.value ?: "",
                    newPassword.value ?: ""
                )) {
                is Resource.Success -> {
                    onSucces.value = true
                }
                is Resource.Error -> {
                    onSucces.value = false
                }
            }
            loadingVisibility.value = false
        }
    }
}