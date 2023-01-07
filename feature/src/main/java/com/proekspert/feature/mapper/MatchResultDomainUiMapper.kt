package com.proekspert.feature.mapper

import com.proekspert.domain.model.MatchResult
import com.proekspert.feature.model.MatchResultUiModel
import com.proekspert.common.Mapper
import javax.inject.Inject

class MatchResultDomainUiMapper @Inject constructor() : Mapper<MatchResult, MatchResultUiModel> {
    override fun from(i: MatchResult?): MatchResultUiModel {
        return MatchResultUiModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResultUiModel?): MatchResult {
        return MatchResult(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            team1_points = o?.team1_points ?: 0,
            team2_points = o?.team2_points ?: 0
        )
    }
}