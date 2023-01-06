package com.proekspert.betting.di

import com.proekspert.data.repository.LocalDataSource
import com.proekspert.data.repository.RemoteDataSource
import com.proekspert.data.repository.RepositoryImp
import com.proekspert.domain.repository.Repository
import com.proekspert.local.sourc.LocalDataSourceImp
import com.proekspert.remote.source.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Module that holds Repository classes
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImp): LocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun provideRepository(repository : RepositoryImp) : Repository

}