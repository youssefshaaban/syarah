package com.tama.domain.repositories

interface ISharedPrefrance {
    fun isOnboardingOpen():Boolean
    fun saveOnboarding(open: Boolean)
    fun getLang():String
    fun setLang(lang:String)
    fun setLanguageSelected(isSelected: Boolean)
    fun isLanguageSelected():Boolean
    fun saveToken(token: String)
    fun getToken():String?
    fun saveRefreshToken(refreshToken: String)
    fun getRefreshToken():String
}