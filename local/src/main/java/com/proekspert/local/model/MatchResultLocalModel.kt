package com.proekspert.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches_results")
data class MatchResultLocalModel(
    @PrimaryKey
    val team1: String,
    @PrimaryKey
    val team2: String,
    val team1_points: Int,
    val team2_points: Int
)
