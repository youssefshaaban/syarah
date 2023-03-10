package com.tama.domain.repository

interface ISharedPrefrance {
    fun isOnboardingOpen():Boolean
    fun saveOnboarding(open: Boolean)
    fun getLang():String
    fun setLang(lang:String)
    fun setLanguageSelected(isSelected: Boolean)
    fun isLanguageSelected():Boolean

}