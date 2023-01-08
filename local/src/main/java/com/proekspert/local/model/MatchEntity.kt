package com.proekspert.local.model

import androidx.room.Entity

@Entity(tableName = "matches",primaryKeys = ["team1", "team2"])
data class MatchEntity(
    val team1 :String,
    val team2 :String,
    val bettingScoreTeam1 : Int?=null,
    val bettingScoreTeam2: Int? =null
)
