package com.example.acronymapp.data.repository

import com.example.acronymapp.data.model.AcronymResponse
import com.example.acronymapp.data.services.AcronymServices
import retrofit2.Response
import javax.inject.Inject

interface Repo {
    suspend fun getAcronym(sf: String): Response<AcronymResponse>
}


class RepoImpl @Inject constructor(private val acronymServices: AcronymServices) : Repo {

    override suspend fun getAcronym(sf: String): Response<AcronymResponse> {
        return acronymServices.getAcronym(sf)
    }


}