package com.proekspert.data.repository

import com.proekspert.common.Mapper
import com.proekspert.common.Resource
import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.domain.entity.MatchResultEntity
import com.proekspert.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val matchMapper: Mapper<MatchDTO, MatchEntity>,
    private val matchResultMapper: Mapper<MatchResultDTO, MatchResultEntity>
) : Repository {
    override suspend fun getMatches(): Flow<Resource<List<MatchEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getAllMatches()
                // Save to local or update if exist
                localDataSource.addAllMatches(data)
                // Emit data
                emit(Resource.Success(matchMapper.fromList(data)))
            } catch (ex : Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getAllMatches()
                    // Emit data
                    emit(Resource.Success(matchMapper.fromList(local)))
                } catch (ex1 : Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }

    override suspend fun getMatchesResults(): Flow<Resource<List<MatchResultEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getAllMatchesResults()
                // Save to local or update if exist
                localDataSource.addAllMatchesResults(data)
                // Emit data
                emit(Resource.Success(matchResultMapper.fromList(data)))
            } catch (ex : Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getAllMatchesResults()
                    // Emit data
                    emit(Resource.Success(matchResultMapper.fromList(local)))
                } catch (ex1 : Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}