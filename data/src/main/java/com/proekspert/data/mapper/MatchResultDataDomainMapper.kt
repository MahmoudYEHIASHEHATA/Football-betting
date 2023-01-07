package com.proekspert.data.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchResultModel
import com.proekspert.domain.model.MatchResult
import javax.inject.Inject

class MatchResultDataDomainMapper @Inject constructor() : Mapper<MatchResultModel, MatchResult> {
    override fun from(i: MatchResultModel?): MatchResult {
        return MatchResult(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResult?): MatchResultModel {
        return MatchResultModel(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            team1_points = o?.team1_points ?: 0,
            team2_points = o?.team2_points ?: 0
        )
    }
}