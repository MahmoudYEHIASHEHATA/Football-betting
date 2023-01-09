package com.proekspert.remote.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchResultModel
import com.proekspert.remote.model.MatchResultDTO
import javax.inject.Inject

class MatchResultNetworkDataMapper @Inject constructor() : Mapper<MatchResultDTO, MatchResultModel> {
    override fun from(i: MatchResultDTO?): MatchResultModel {
        return MatchResultModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResultModel?): MatchResultDTO {
        return MatchResultDTO()
    }
}
