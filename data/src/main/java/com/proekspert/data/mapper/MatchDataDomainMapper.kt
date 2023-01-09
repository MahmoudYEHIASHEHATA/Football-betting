package com.proekspert.data.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchModel
import com.proekspert.domain.model.Match
import javax.inject.Inject

class MatchDataDomainMapper @Inject constructor() : Mapper<MatchModel, Match> {
    override fun from(i: MatchModel?): Match {
        return Match(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            bettingScoreTeam1 = i?.bettingScoreTeam1,
            bettingScoreTeam2 = i?.bettingScoreTeam2
        )
    }

    override fun to(o: Match?): MatchModel {
        return MatchModel(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            bettingScoreTeam1 = o?.bettingScoreTeam1,
            bettingScoreTeam2 = o?.bettingScoreTeam2
        )
    }
}