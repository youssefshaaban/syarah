package com.tama.data.network

import com.tama.domain.repository.ISharedPrefrance
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticatorInterceptor @Inject constructor(val iSharedPrefrance: ISharedPrefrance) :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        if (iSharedPrefrance.getToken().isEmpty().not())
            requestBuilder.addHeader("Authorization", "Bearer ${iSharedPrefrance.getToken()}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}