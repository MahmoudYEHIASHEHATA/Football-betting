package com.proekspert.domain.usecase

import com.proekspert.domain.repository.Repository
import javax.inject.Inject

class CheckExistsPredictionsInLocalUseCase @Inject constructor(
    private val repository: Repository,
) {

     suspend fun buildRequest(): Boolean {
        return repository.existsPredictions()
    }
}