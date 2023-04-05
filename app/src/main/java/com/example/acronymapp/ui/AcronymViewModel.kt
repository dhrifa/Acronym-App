package com.example.acronymapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronymapp.data.repository.Repo
import com.example.acronymapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val repo: Repo
    ): ViewModel() {

    private val _meaning: MutableLiveData<UIState> = MutableLiveData()
    val meaning: LiveData<UIState> get() = _meaning

    fun getMeaning(acronym: String){
        CoroutineScope(Dispatchers.IO).launch {
            _meaning.postValue(
                repo.getAcronym(acronym).body()?.let { UIState.SUCCESS(it) }
//            UIState.SUCCESS(repoImpl.getAcronym(acronym).body() ?: listOf())
            )
        }
    }
}