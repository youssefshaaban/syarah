package com.tama.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tama.data.BuildConfig
import com.tama.data.network.AuthenticatorInterceptor
import com.tama.data.network.HeaderInterceptor
import com.tama.data.remote.DriverApi
import com.tama.data.remote.IdentityService
import com.tama.data.util.BASE_URl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val timeoutConnect = 30
    private const val timeoutRead = 30
    @Provides
    fun provideOkHttpClient(authenticatorInterceptor: AuthenticatorInterceptor,headerInterceptor: HeaderInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        }
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(authenticatorInterceptor)
        okHttpBuilder.addInterceptor(headerInterceptor)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideIdentityApi(retrofit: Retrofit): IdentityService {
        return retrofit.create(IdentityService::class.java)
    }
    @Provides
    @Singleton
    fun provideDriverApi(retrofit: Retrofit): DriverApi {
        return retrofit.create(DriverApi::class.java)
    }

}