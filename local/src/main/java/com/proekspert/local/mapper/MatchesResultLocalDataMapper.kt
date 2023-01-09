package com.proekspert.local.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchResultModel
import com.proekspert.local.model.MatchResultEntity
import javax.inject.Inject

class MatchesResultLocalDataMapper @Inject constructor() :
    Mapper<MatchResultEntity, MatchResultModel> {
    override fun from(i: MatchResultEntity?): MatchResultModel {
        return MatchResultModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResultModel?): MatchResultEntity {
        return MatchResultEntity(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            team1_points = o?.team1_points ?: 0,
            team2_points = o?.team2_points ?: 0
        )
    }
}