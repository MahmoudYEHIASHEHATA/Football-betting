package com.proekspert.betting.di

import com.proekspert.qualifiers.DefaultDispatcher
import com.proekspert.qualifiers.IoDispatcher
import com.proekspert.qualifiers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Module that holds Dispatchers
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @DefaultDispatcher
    @Provides
    internal fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    internal fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}