package com.proekspert.data.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.domain.entity.MatchResultEntity
import javax.inject.Inject

class MatchResultDataDomainMapper @Inject constructor() : Mapper<MatchResultDTO, MatchResultEntity> {
    override fun from(i: MatchResultDTO?): MatchResultEntity {
        return MatchResultEntity(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_",
            team1_points = i?.team1_points ?: 0,
            team2_points = i?.team2_points ?: 0
        )
    }

    override fun to(o: MatchResultEntity?): MatchResultDTO {
        return MatchResultDTO(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_",
            team1_points = o?.team1_points ?: 0,
            team2_points = o?.team2_points ?: 0
        )
    }
}