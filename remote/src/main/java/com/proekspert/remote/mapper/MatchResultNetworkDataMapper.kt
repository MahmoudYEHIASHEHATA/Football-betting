package com.proekspert.remote.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.remote.model.Match
import com.proekspert.remote.model.MatchResult
import javax.inject.Inject

class MatchResultNetworkDataMapper @Inject constructor() : Mapper<MatchResult, MatchResultDTO> {
    override fun from(i: MatchResult?): MatchResultDTO {
        return MatchResultDTO(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResultDTO?): MatchResult {
        return MatchResult()
    }
}
