package com.tama.data.di

import com.tama.data.repositeries.WorkerRepositoryImp
import com.tama.data.repositeries.IdentityRepositoryImp
import com.tama.domain.repositories.WorkerRepository
import com.tama.domain.repositories.IdentityRepository
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
    abstract fun bindDriverRepository(workerRepository: WorkerRepositoryImp): WorkerRepository
}