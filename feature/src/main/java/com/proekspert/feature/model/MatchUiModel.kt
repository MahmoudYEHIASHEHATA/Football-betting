package com.proekspert.feature.model

import android.os.Parcelable
import java.io.Serializable

data class MatchUiModel(
    val team1: String,
    val team2: String,
    val bettingScoreTeam1: Int? = null,
    val bettingScoreTeam2: Int? = null
) :Serializable
