package com.proekspert.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.proekspert.base.BaseViewModel
import com.proekspert.common.Mapper
import com.proekspert.common.Resource
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.domain.usecase.GetMatchesUseCase
import com.proekspert.feature.contract.MatchesForPredictionContract
import com.proekspert.feature.model.MatchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesForPredictionViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase,
    private val matchMapper: Mapper<MatchEntity, MatchUiModel>
) : BaseViewModel<MatchesForPredictionContract.Event, MatchesForPredictionContract.State, MatchesForPredictionContract.Effect>() {

    override fun createInitialState(): MatchesForPredictionContract.State {
        return MatchesForPredictionContract.State(
            matchState = MatchesForPredictionContract.MatchState.Idle,
            selectedMatch = null
        )
    }

    override fun handleEvent(event: MatchesForPredictionContract.Event) {
        when (event) {
            is MatchesForPredictionContract.Event.OnFetchAllMatchesForPrediction -> {
                getAllMatches()
            }
            is MatchesForPredictionContract.Event.OnMatchItemClicked -> {
                val item = event.match
                setSelectedMatch(match = item)
            }
        }
    }

    /**
     * Fetch matches
     */
    private fun getAllMatches() {
        viewModelScope.launch {
            getMatchesUseCase.execute(null)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            setState { copy(matchState = MatchesForPredictionContract.MatchState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(matchState = MatchesForPredictionContract.MatchState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set State
                            val matchesList = matchMapper.fromList(it.data)
                            setState {
                                copy(
                                    matchState = MatchesForPredictionContract.MatchState.Success(
                                        matchesList = matchesList
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { MatchesForPredictionContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }

    /**
     * Set selected match item
     */
    private fun setSelectedMatch(match: MatchUiModel?) {
        // Set State
        setState { copy(selectedMatch = match) }
    }
}