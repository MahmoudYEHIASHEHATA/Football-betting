package com.proekspert.feature.contract

import com.proekspert.base.UiEffect
import com.proekspert.base.UiEvent
import com.proekspert.base.UiState
import com.proekspert.feature.model.MatchUiModel


/**
 * Contract of Main Screen
 */
class MatchesForPredictionContract {

    sealed class Event : UiEvent {
        object OnFetchAllMatchesForPrediction : Event()
        data class OnMatchItemClicked(val match: MatchUiModel) : Event()
    }

    data class State(
        val matchState: MatchState,
        val selectedMatch: MatchUiModel? = null
    ) : UiState

    sealed class MatchState {
        object Idle : MatchState()
        object Loading : MatchState()
        data class Success(val matchesList: List<MatchUiModel>) : MatchState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}