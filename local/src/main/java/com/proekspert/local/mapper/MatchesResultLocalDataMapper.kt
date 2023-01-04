package com.proekspert.local.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.local.model.MatchResultLocalModel
import javax.inject.Inject

class MatchesResultLocalDataMapper @Inject constructor() :
    Mapper<MatchResultLocalModel, MatchResultDTO> {
    override fun from(i: MatchResultLocalModel?): MatchResultDTO {
        return MatchResultDTO(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResultDTO?): MatchResultLocalModel {
        return MatchResultLocalModel(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            team1_points = o?.team1_points ?: 0,
            team2_points = o?.team2_points ?: 0
        )
    }
}