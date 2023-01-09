package com.proekspert.domain.usecase

import com.proekspert.domain.model.MatchResultsWithPrediction
import com.proekspert.domain.repository.Repository
import com.proekspert.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetMatchesResultsWithPredictionUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<List<MatchResultsWithPrediction>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<List<MatchResultsWithPrediction>> {
        return getMatchesResultsWithPrediction()
    }

    private suspend fun getMatchesResultsWithPrediction(): Flow<List<MatchResultsWithPrediction>> {
        var mapPredictions: HashMap<Pair<String, String>, Pair<Int?, Int?>> = HashMap()
        val matchesPredictions = repository.getCachedMatches()
        val matchesResults =
            if (repository.isMatchesResultsTableEmpty()) repository.getFreshMatchesResults() else repository.getCachedMatchesResults()

        return matchesResults.combine(matchesPredictions) { results, predictions ->
            for (prediction in predictions) {
                mapPredictions[Pair(prediction.team1, prediction.team2)] =
                    Pair(prediction.bettingScoreTeam1, prediction.bettingScoreTeam2)
            }
            results.map {
                MatchResultsWithPrediction(
                    it.team1,
                    it.team2,
                    it.team1_points,
                    it.team2_points,
                    mapPredictions[Pair(it.team1, it.team2)]?.first,
                    mapPredictions[Pair(it.team1, it.team2)]?.second
                )
            }
        }
    }
}