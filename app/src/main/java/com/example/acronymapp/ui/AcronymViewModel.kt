package com.example.acronymapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymapp.usecase.MeaningUseCase
import com.example.acronymapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val meaningUseCase: MeaningUseCase,
    @Named("io")
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _meaning: MutableLiveData<UIState> = MutableLiveData()
    val meaning: LiveData<UIState> get() = _meaning

    val acronym =""
    fun getMeaning(
        acronym: String
    ) {
        viewModelScope.launch(ioDispatcher) {
            meaningUseCase.retrieveMeaning(
                acronym,
                success = {_meaning.postValue(it)},
                error =  {_meaning.postValue(it)},
                loading = {_meaning.postValue(it)}
            )
        }
    }


}