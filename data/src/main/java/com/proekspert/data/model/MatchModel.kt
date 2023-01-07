package com.proekspert.data.model

data class MatchModel(
    val team1: String,
    val team2: String,
    val bettingScoreTeam1 : Int?=null,
    val bettingScoreTeam2: Int? =null
)
