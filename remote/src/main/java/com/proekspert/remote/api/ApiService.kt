package com.proekspert.remote.api

import com.proekspert.remote.model.MatchesResponseNetwork
import com.proekspert.remote.model.MatchesResultsResponseNetwork
import retrofit2.http.GET

interface ApiService {

    @GET("40522652-5bef-49e3-a148-719a9034aa18")
    suspend fun getMatches() : MatchesResponseNetwork

    @GET("93d3aa18-94af-4c30-a26b-9f686011c572")
    suspend fun getMatchesResults() : MatchesResultsResponseNetwork
}