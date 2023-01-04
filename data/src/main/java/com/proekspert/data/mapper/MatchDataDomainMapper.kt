package com.proekspert.data.mapper

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.domain.entity.MatchEntity
import javax.inject.Inject

class MatchDataDomainMapper @Inject constructor() : Mapper<MatchDTO, MatchEntity> {
    override fun from(i: MatchDTO?): MatchEntity {
        return MatchEntity(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_"
        )
    }

    override fun to(o: MatchEntity?): MatchDTO {
        return MatchDTO(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_"
        )
    }
}