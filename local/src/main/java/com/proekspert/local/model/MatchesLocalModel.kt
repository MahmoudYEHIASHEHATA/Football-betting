package com.proekspert.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchesLocalModel(
    @PrimaryKey
    val team1 :String,
    @PrimaryKey
    val team2 :String
)
