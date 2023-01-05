package com.proekspert.feature.mapper

import com.proekspert.common.Mapper
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.feature.model.MatchUiModel
import javax.inject.Inject

class MatchDomainUiMapper @Inject constructor() : Mapper<MatchEntity, MatchUiModel> {
    override fun from(i: MatchEntity?): MatchUiModel {
        return MatchUiModel(
            team1 = i?.team1 ?: "_",
            team2 = i?.team2 ?: "_"
        )
    }

    override fun to(o: MatchUiModel?): MatchEntity {
        return MatchEntity(
            team1 = o?.team1 ?: "_",
            team2 = o?.team2 ?: "_"
        )
    }
}