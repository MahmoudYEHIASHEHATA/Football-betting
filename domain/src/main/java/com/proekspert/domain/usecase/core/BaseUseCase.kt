package com.proekspert.domain.usecase.core

import androidx.annotation.Nullable
import kotlinx.coroutines.flow.Flow

/**
 * Base Use Case class
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(@Nullable params: Params?): Flow<Model>

    suspend fun execute(@Nullable params: Params?): Flow<Model> {
           return buildRequest(params)
        }
    }