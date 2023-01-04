package com.proekspert.local.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.local.model.MatchLocalModel
import javax.inject.Inject

class MatchesLocalDataMapper @Inject constructor() : Mapper<MatchLocalModel, MatchDTO> {
    override fun from(i: MatchLocalModel?): MatchDTO {
        return MatchDTO(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_"
        )
    }

    override fun to(o: MatchDTO?): MatchLocalModel {
        return MatchLocalModel(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_"
        )
    }
}