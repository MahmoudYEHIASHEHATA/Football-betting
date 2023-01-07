package com.proekspert.feature.contract

import com.proekspert.base.UiEffect
import com.proekspert.base.UiEvent
import com.proekspert.base.UiState
import com.proekspert.feature.model.MatchUiModel


/**
 * Contract of Betting Dialog
 */
class BettingContract {

    sealed class Event : UiEvent {
        data class SetMatch(val match: MatchUiModel?) : Event()
        object AddScoreTeam1 : Event()
        object AddScoreTeam2 : Event()
        object MinusScoreTeam1 : Event()
        object MinusScoreTeam2 : Event()
        data class UpdateMatch(val match: MatchUiModel?) : Event()
    }

    data class State(
        val matchState: MatchUiModel?,

    ) : UiState

    sealed class Effect : UiEffect {
       object DismissDialog: Effect()
    }
}