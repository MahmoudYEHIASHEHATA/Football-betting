package com.proekspert.remote.utils

import com.proekspert.remote.model.MatchDTO
import com.proekspert.remote.model.MatchResultDTO
import com.proekspert.remote.model.MatchesResponseNetwork
import com.proekspert.remote.model.MatchesResultsResponseNetwork


/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun generateMatches(): MatchesResponseNetwork {
            return MatchesResponseNetwork(
                matches = listOf(
                    MatchDTO(
                        team2 = "Real",
                        team1 = "City"
                    )
                )
            )
        }

        fun generateMatchResults(): MatchesResultsResponseNetwork {
            return MatchesResultsResponseNetwork(
                matches = listOf(
                    MatchResultDTO(
                        team2 = "Real",
                        team1 = "City",
                        team2_points = 1,
                        team1_points = 2
                    )
                )
            )
        }
    }

}