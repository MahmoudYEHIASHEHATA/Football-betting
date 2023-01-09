package com.proekspert.local.utils

import com.proekspert.local.model.MatchEntity
import com.proekspert.local.model.MatchResultEntity


/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun generateMatchItems(): List<MatchEntity> {
            val item1 = MatchEntity("Real", "City", 1, 2)
            val item2 = MatchEntity("Real", "Liver", 3, 5)
            val item3 = MatchEntity("PRC", "City", 1, 2)
            return listOf(item1, item2, item3)
        }

        fun generateMatchResultItems(): List<MatchResultEntity> {
            val item1 =
                MatchResultEntity("Real", "City", 3, 2)
            val item2 =
                MatchResultEntity("Real", "Liver", 0, 2)
            val item3 =
                MatchResultEntity("Real", "lat", 0, 1)
            return listOf(item1, item2, item3)

        }
    }

}