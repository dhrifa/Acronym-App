package com.example.acronymapp.data.repository

import com.example.acronymapp.data.services.AcronymServices
import com.example.acronymapp.util.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AcronymRepo {
    fun getAcronymMeaning(sf: String): Flow<UIState>//Response<AcronymResponse>
}


class AcronymRepoImpl @Inject constructor(
    private val acronymServices: AcronymServices
) : AcronymRepo {
    override fun getAcronymMeaning(sf: String): Flow<UIState> = flow {//Response<AcronymResponse> {
//        return acronymServices.getAcronym(sf)
        emit(UIState.LOADING)
        try {
            val response = acronymServices.getAcronymMeaning(sf)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                } ?: throw Exception("response null")
            } else {
                throw Exception(response.errorBody()?.toString())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}
