package com.tama.customui.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.tama.customui.databinding.LayoutSearchViewBinding
import kotlinx.coroutines.flow.*

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

    private var textInputEditText: EditText? = null
    private val binding: LayoutSearchViewBinding =
        LayoutSearchViewBinding.inflate(
            LayoutInflater.from(context), this, true
        ).apply {
            viewModel = searchCustomViewViewModel
            this@SearchBarCustomView.textInputEditText = textInputEditText
        }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.lifecycleOwner = findViewTreeLifecycleOwner()
        searchCustomViewViewModel.clearText.observe(findViewTreeLifecycleOwner()!!) { event ->
            event?.also { shouldClearText ->
                if (shouldClearText) {
                    textInputEditText?.setText("")
                }
            }
        }
    }

    init {
        textInputEditText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not()) hideKeyboard()
        }
        textInputEditText?.doOnTextChanged { text, _, _, _ ->
            searchCustomViewViewModel.onSearchTextChanged(text.toString())
        }

    }


    fun setSearchHint(hint: String) {
        textInputEditText?.hint = hint
    }

    fun setSearchAction(action: SearchAction) {
        searchCustomViewViewModel.searchAction = action
    }
    fun getQueryTextChangeStateFlow(): StateFlow<String> {
        return searchCustomViewViewModel.query
    }
}