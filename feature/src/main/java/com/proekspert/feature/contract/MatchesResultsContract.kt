package com.proekspert.feature.contract

import com.proekspert.base.UiEffect
import com.proekspert.base.UiEvent
import com.proekspert.base.UiState
import com.proekspert.feature.model.MatchResultUiModel

/**
 * Contract of Main Screen
 */
class MatchesResultsContract {

    sealed class Event : UiEvent {
        object OnFetchAllMatchesResults : Event()
        object Restart :Event()
    }

    data class State(
        val matchResultState: MatchResultState,
    ) : UiState

    sealed class MatchResultState {
        object Idle : MatchResultState()
        object Loading : MatchResultState()
        data class Success(val matchesList: List<MatchResultUiModel>) : MatchResultState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
        object PopUpResultFragment : Effect()
    }
}