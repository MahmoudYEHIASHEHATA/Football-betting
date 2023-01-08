package com.proekspert.feature.model

data class MatchResultUiModel(
    val team1: String,
    val team2: String,
    val team1_points: Int,
    val team2_points: Int,
    val bettingScoreTeam1 : Int?=null,
    val bettingScoreTeam2: Int? =null
)
