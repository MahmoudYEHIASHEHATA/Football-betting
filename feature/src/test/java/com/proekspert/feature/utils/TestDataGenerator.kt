package com.proekspert.feature.utils

import com.proekspert.domain.model.Match


class TestDataGenerator {

    companion object {
        fun generateMatchesItems(): List<Match> {
            val item1 = Match("Real", "City", 1, 2)
            val item2 = Match("Real", "Liver", 3, 5)
            val item3 = Match("PRC", "City", 1, 2)
            return listOf(item1, item2, item3)
        }
    }

}