package com.tama.data.di

import com.tama.data.local.AppPrefrancesImp
import com.tama.domain.repositories.ISharedPrefrance
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class SharedPrefrancesModule {
    @Binds
    @Singleton
    abstract fun bindAppprefrances(appPrefrancesImp: AppPrefrancesImp): ISharedPrefrance
}