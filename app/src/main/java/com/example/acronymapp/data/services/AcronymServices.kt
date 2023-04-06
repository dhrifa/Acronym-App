package com.example.acronymapp.data.services

import com.example.acronymapp.data.model.AcronymResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymServices {

    @GET(SF_PATH)
    suspend fun getAcronymMeaning(
        @Query(SF_PARAM) acronym: String
    ): Response<AcronymResponse>

    companion object {
        //http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM

        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        private const val SF_PATH = "dictionary.py"
        const val SF_PARAM = "sf"
        const val LF_PARAM = "lf"

    }

}