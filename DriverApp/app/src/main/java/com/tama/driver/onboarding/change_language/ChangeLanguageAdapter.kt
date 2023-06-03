package com.tama.driver.onboarding.change_language

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import com.tama.driver.R

object ChangeLanguageAdapter {

    @BindingAdapter("app:bindSelection")
    @JvmStatic
    fun bindSelectionLanguage(imageView: ImageView,selction: Boolean?){
        if(selction==true){
            imageView.setImageResource(R.drawable.ic_selection)
        }else{
            imageView.setImageResource(R.drawable.ic_unselection_raduis)
        }
    }
    @JvmStatic
    @BindingAdapter("app:bindSelectionContent")
    fun bindSelectionLanguage(content: LinearLayoutCompat, selction: Boolean?){
        if (selction==true){
            content.background = AppCompatResources.getDrawable(content.context,R.drawable.background_strock_selection)
        }else{
            content.background = AppCompatResources.getDrawable(content.context,R.drawable.background_strock_unselection)
        }

    }
}