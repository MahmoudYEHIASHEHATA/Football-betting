package com.proekspert.betting.di

import com.proekspert.common.Mapper
import com.proekspert.data.mapper.MatchDataDomainMapper
import com.proekspert.data.mapper.MatchResultDataDomainMapper
import com.proekspert.data.model.MatchModel
import com.proekspert.data.model.MatchResultModel
import com.proekspert.domain.model.Match
import com.proekspert.domain.model.MatchResult
import com.proekspert.feature.mapper.MatchDomainUiMapper
import com.proekspert.feature.mapper.MatchResultDomainUiMapper
import com.proekspert.feature.model.MatchResultUiModel
import com.proekspert.feature.model.MatchUiModel
import com.proekspert.local.mapper.MatchesLocalDataMapper
import com.proekspert.local.mapper.MatchesResultLocalDataMapper
import com.proekspert.local.model.MatchEntity
import com.proekspert.local.model.MatchResultEntity
import com.proekspert.remote.mapper.MatchNetworkDataMapper
import com.proekspert.remote.mapper.MatchResultNetworkDataMapper
import com.proekspert.remote.model.MatchDTO
import com.proekspert.remote.model.MatchResultDTO
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
    abstract fun bindsMatchesLocalDataMapper(mapper : MatchesLocalDataMapper) : Mapper<MatchEntity, MatchModel>

    @Binds
    abstract fun bindsMatchesResultsLocalDataMapper(mapper : MatchesResultLocalDataMapper) : Mapper<MatchResultEntity, MatchResultModel>
    //endregion


    //region Data Mappers
    @Binds
    abstract fun  bindsMatchesDataDomainMapper(mapper : MatchDataDomainMapper) : Mapper<MatchModel, Match>

    @Binds
    abstract fun bindsMatchResultDataDomainMapper(mapper : MatchResultDataDomainMapper) : Mapper<MatchResultModel, MatchResult>
    //endregion

    //region Presentation Mappers
    @Binds
    abstract fun bindsMatchDomainUiMapper(mapper : MatchDomainUiMapper) : Mapper<Match, MatchUiModel>

    @Binds
    abstract fun bindsMatchResultDomainUiMapper(mapper : MatchResultDomainUiMapper) : Mapper<MatchResult, MatchResultUiModel>
    //endregion


    //region Remote Mappers

    @Binds
    abstract fun bindsMatchNetworkDataMapper(mapper: MatchNetworkDataMapper): Mapper<MatchDTO, MatchModel>

    @Binds
    abstract fun bindsMatchResultNetworkDataMapper(mapper: MatchResultNetworkDataMapper): Mapper<MatchResultDTO, MatchResultModel>
    //endregion

}