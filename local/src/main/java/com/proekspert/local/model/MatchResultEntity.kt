package com.proekspert.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches_results",primaryKeys = ["team1", "team2"])
data class MatchResultEntity(
    val team1: String,
    val team2: String,
    val team1_points: Int,
    val team2_points: Int
)
