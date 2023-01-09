package com.proekspert.remote.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchModel
import com.proekspert.remote.model.MatchDTO
import javax.inject.Inject

class MatchNetworkDataMapper @Inject constructor() : Mapper<MatchDTO, MatchModel> {
    override fun from(i: MatchDTO?): MatchModel {
        return MatchModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_"
        )
    }

    override fun to(o: MatchModel?): MatchDTO {
        return MatchDTO()
    }
}
