package com.proekspert.feature.mapper

import com.proekspert.feature.model.MatchResultUiModel
import com.proekspert.common.Mapper
import com.proekspert.domain.model.MatchResultsWithPrediction
import javax.inject.Inject

class MatchResultDomainUiMapper @Inject constructor() : Mapper<MatchResultsWithPrediction, MatchResultUiModel> {
    override fun from(i: MatchResultsWithPrediction?): MatchResultUiModel {
        return MatchResultUiModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0,
            bettingScoreTeam1 = i?.bettingScoreTeam1,
            bettingScoreTeam2 = i?.bettingScoreTeam2
        )
    }

    override fun to(o: MatchResultUiModel?): MatchResultsWithPrediction {
        return MatchResultsWithPrediction(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            team1_points = o?.team1_points ?: 0,
            team2_points = o?.team2_points ?: 0,
            bettingScoreTeam1 = o?.bettingScoreTeam1,
            bettingScoreTeam2 = o?.bettingScoreTeam2
        )
    }
}