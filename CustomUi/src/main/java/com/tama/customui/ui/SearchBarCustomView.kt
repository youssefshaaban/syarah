package com.tama.customui.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.lifecycle.LifecycleOwner
import com.tama.customui.databinding.LayoutSearchViewBinding

@BindingMethods(
    BindingMethod(
        type = SearchBarCustomView::class,
        attribute = "app:hintByKey",
        method = "setSearchHint"
    )
)
class SearchBarCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    fun interface SearchAction {
        fun search(key: String)
    }
    private val searchCustomViewViewModel = SearchViewModel()

    private var textInputEditText: EditText?=null
    private val binding: LayoutSearchViewBinding =
        LayoutSearchViewBinding.inflate(
            LayoutInflater.from(context), this, true
        ).apply {
            viewModel = searchCustomViewViewModel
            this@SearchBarCustomView.textInputEditText = textInputEditText
            if (context is LifecycleOwner) {
                lifecycleOwner = context
            }
        }


    init {
        textInputEditText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not()) hideKeyboard()
        }
        textInputEditText?.doOnTextChanged { text, _, _, _ ->
            searchCustomViewViewModel.onSearchTextChanged(text.toString())
        }
        binding.lifecycleOwner?.let {
            searchCustomViewViewModel.clearText.observe(it) { event ->
                event?.also { shouldClearText ->
                    if (shouldClearText) {
                        textInputEditText?.setText("")
                    }
                }
            }
        }
    }


    fun setSearchHint(hint: String) {
        textInputEditText?.hint = hint
    }

    fun setSearchAction(action: SearchAction) {
        searchCustomViewViewModel.searchAction = action
    }


}