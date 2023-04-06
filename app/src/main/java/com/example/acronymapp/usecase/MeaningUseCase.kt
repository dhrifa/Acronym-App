package com.example.acronymapp.usecase

import com.example.acronymapp.data.repository.AcronymRepo
import com.example.acronymapp.util.UIState
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MeaningUseCase @Inject constructor(
    private val repo: AcronymRepo
) {
    suspend fun retrieveMeaning(
        shortFormat: String,
//        state: (UIState) -> Unit
        success: (UIState.SUCCESS) -> Unit,
        error: (UIState.ERROR) -> Unit,
        loading: (UIState.LOADING) -> Unit
    ) {
        repo.getAcronymMeaning(shortFormat).collect {
//            state.invoke(it)
            when(it){
                is UIState.LOADING->{ loading.invoke(it) }
                is UIState.SUCCESS->{ success(it) }
                is UIState.ERROR->{ error(it)}

            }
        }
    }
}