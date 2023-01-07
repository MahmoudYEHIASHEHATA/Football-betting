package com.proekspert.domain.usecase.core

import androidx.annotation.Nullable
import com.proekspert.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base Use Case class
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(@Nullable params: Params?): Flow<Model>

    suspend fun execute(@Nullable params: Params?): Flow<Model> {
           return buildRequest(params)
        }
    }