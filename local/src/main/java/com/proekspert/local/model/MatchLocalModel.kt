package com.proekspert.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches",primaryKeys = ["team1", "team2"])
data class MatchLocalModel(
    val team1 :String,
    val team2 :String
)
