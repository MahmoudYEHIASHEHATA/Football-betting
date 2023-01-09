package com.proekspert.feature.mapper

import com.proekspert.common.Mapper
import com.proekspert.domain.model.Match
import com.proekspert.feature.model.MatchUiModel
import javax.inject.Inject

class MatchDomainUiMapper @Inject constructor() : Mapper<Match, MatchUiModel> {
    override fun from(i: Match?): MatchUiModel {
        return MatchUiModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            bettingScoreTeam1 = i?.bettingScoreTeam1,
            bettingScoreTeam2 = i?.bettingScoreTeam2
        )
    }

    override fun to(o: MatchUiModel?): Match {
        return Match(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            bettingScoreTeam1 = o?.bettingScoreTeam1,
            bettingScoreTeam2 = o?.bettingScoreTeam2
        )
    }
}