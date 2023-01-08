package com.proekspert.domain.model

data class MatchResultsWithPrediction(
    val team1:String,
    val team2:String,
    val team1_points: Int,
    val team2_points: Int,
    val bettingScoreTeam1 : Int?,
    val bettingScoreTeam2: Int?
)
