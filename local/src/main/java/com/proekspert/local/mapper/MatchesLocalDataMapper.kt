package com.proekspert.local.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchModel
import com.proekspert.local.model.MatchEntity
import javax.inject.Inject

class MatchesLocalDataMapper @Inject constructor() : Mapper<MatchEntity, MatchModel> {
    override fun from(i: MatchEntity?): MatchModel {
        return MatchModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            bettingScoreTeam1 = i?.bettingScoreTeam1,
            bettingScoreTeam2 = i?.bettingScoreTeam2
        )
    }

    override fun to(o: MatchModel?): MatchEntity {
        return MatchEntity(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            bettingScoreTeam1 = o?.bettingScoreTeam1,
            bettingScoreTeam2 = o?.bettingScoreTeam2
        )
    }
}