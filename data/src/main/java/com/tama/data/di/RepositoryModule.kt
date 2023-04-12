package com.tama.data.di

import com.tama.data.local.AppPrefrancesImp
import com.tama.data.repositeries.DriverRepositoryImp
import com.tama.data.repositeries.IdentityRepositoryImp
import com.tama.domain.repository.DriverRepository
import com.tama.domain.repository.ISharedPrefrance
import com.tama.domain.repository.IdentityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun binIdentityRepository(identityRepository: IdentityRepositoryImp): IdentityRepository
    @Binds
    @Singleton
    abstract fun bindDriverRepository(driverRepository: DriverRepositoryImp): DriverRepository
}