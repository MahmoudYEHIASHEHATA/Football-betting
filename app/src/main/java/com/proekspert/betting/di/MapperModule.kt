package com.proekspert.betting.di

import com.proekspert.common.Mapper
import com.proekspert.data.mapper.MatchDataDomainMapper
import com.proekspert.data.mapper.MatchResultDataDomainMapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.domain.entity.MatchResultEntity
import com.proekspert.feature.mapper.MatchDomainUiMapper
import com.proekspert.feature.mapper.MatchResultDomainUiMapper
import com.proekspert.feature.model.MatchResultUiModel
import com.proekspert.feature.model.MatchUiModel
import com.proekspert.local.mapper.MatchesLocalDataMapper
import com.proekspert.local.mapper.MatchesResultLocalDataMapper
import com.proekspert.local.model.MatchLocalModel
import com.proekspert.local.model.MatchResultLocalModel
import com.proekspert.remote.mapper.MatchNetworkDataMapper
import com.proekspert.remote.mapper.MatchResultNetworkDataMapper
import com.proekspert.remote.model.Match
import com.proekspert.remote.model.MatchResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Locale Mappers
    @Binds
    abstract fun bindsMatchesLocalDataMapper(mapper : MatchesLocalDataMapper) : Mapper<MatchLocalModel, MatchDTO>

    @Binds
    abstract fun bindsMatchesResultsLocalDataMapper(mapper : MatchesResultLocalDataMapper) : Mapper<MatchResultLocalModel, MatchResultDTO>
    //endregion


    //region Data Mappers
    @Binds
    abstract fun  bindsMatchesDataDomainMapper(mapper : MatchDataDomainMapper) : Mapper<MatchDTO, MatchEntity>

    @Binds
    abstract fun bindsMatchResultDataDomainMapper(mapper : MatchResultDataDomainMapper) : Mapper<MatchResultDTO, MatchResultEntity>
    //endregion

    //region Presentation Mappers
    @Binds
    abstract fun bindsMatchDomainUiMapper(mapper : MatchDomainUiMapper) : Mapper<MatchEntity, MatchUiModel>

    @Binds
    abstract fun bindsMatchResultDomainUiMapper(mapper : MatchResultDomainUiMapper) : Mapper<MatchResultEntity, MatchResultUiModel>
    //endregion


    //region Remote Mappers

    @Binds
    abstract fun bindsMatchNetworkDataMapper(mapper: MatchNetworkDataMapper): Mapper<Match, MatchDTO>

    @Binds
    abstract fun bindsMatchResultNetworkDataMapper(mapper: MatchResultNetworkDataMapper): Mapper<MatchResult, MatchResultDTO>
    //endregion

}