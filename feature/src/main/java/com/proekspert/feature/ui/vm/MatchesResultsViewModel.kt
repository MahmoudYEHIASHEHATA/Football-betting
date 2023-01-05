package com.proekspert.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.proekspert.base.BaseViewModel
import com.proekspert.common.Mapper
import com.proekspert.common.Resource
import com.proekspert.domain.entity.MatchResultEntity
import com.proekspert.domain.usecase.GetMatchesResultsUseCase
import com.proekspert.feature.contract.MatchesResultsContract
import com.proekspert.feature.model.MatchResultUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesResultsViewModel @Inject constructor(
    private val getMatchesResultsUseCase: GetMatchesResultsUseCase,
    private val matchMapper: Mapper<MatchResultEntity, MatchResultUiModel>
) : BaseViewModel<MatchesResultsContract.Event, MatchesResultsContract.State, MatchesResultsContract.Effect>() {

    override fun createInitialState(): MatchesResultsContract.State {
        return MatchesResultsContract.State(
            matchResultState = MatchesResultsContract.MatchResultState.Idle,
        )
    }

    override fun handleEvent(event: MatchesResultsContract.Event) {
        when (event) {
            is MatchesResultsContract.Event.OnFetchAllMatchesResults -> {
                getAllMatchesResults()
            }
        }
    }

    /**
     * Fetch matches results
     */
    private fun getAllMatchesResults() {
        viewModelScope.launch {
            getMatchesResultsUseCase.execute(null)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            setState { copy(matchResultState = MatchesResultsContract.MatchResultState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(matchResultState = MatchesResultsContract.MatchResultState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set State
                            val matchesList = matchMapper.fromList(it.data)
                            setState {
                                copy(
                                    matchResultState = MatchesResultsContract.MatchResultState.Success(
                                        matchesList = matchesList
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { MatchesResultsContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }
}