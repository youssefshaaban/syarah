package com.tama.customui.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.*


class SearchViewModel : ViewModel() {
    var searchAction: SearchBarCustomView.SearchAction? = null
    val clearText = MutableLiveData<Boolean>()
    val shouldShowClearButton = MediatorLiveData<Boolean>().apply { value = true }
    private val searchKey = MutableLiveData<String>().apply { value = "" }
    val query = MutableStateFlow("")

    init {
        shouldShowClearButton.addSource(searchKey) {
            shouldShowClearButton.value = !it.isNullOrEmpty()
        }
    }

    fun onSearchTextChanged(text: String) {
        searchKey.value = text
        query.value = text
        //searchAction?.search(text)
    }

    fun onRightButtonClick() {
        if (!searchKey.value.isNullOrEmpty()) {
            clearText.value = true
        }
    }
}