package com.proekspert.remote.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.remote.model.Match
import javax.inject.Inject

class MatchNetworkDataMapper @Inject constructor() : Mapper<Match, MatchDTO> {
    override fun from(i: Match?): MatchDTO {
        return MatchDTO(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_"
        )
    }

    override fun to(o: MatchDTO?): Match {
        return Match()
    }
}
