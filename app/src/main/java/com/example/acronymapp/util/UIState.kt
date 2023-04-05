package com.example.acronymapp.util

import com.example.acronymapp.data.model.AcronymResponseItem


sealed class UIState{

    object LOADING: UIState()
    data class SUCCESS(val response: List<AcronymResponseItem>): UIState()
    data class ERROR(val error: Exception): UIState()

}

//
//sealed class UIState<T>(
//    val data: T? = null,
//    val message: String? = null
//){
//    class SUCCESS<T>(data: T?): UIState<T>(data)
//
//    class ERROR<T>(message: String?): UIState<T>(message = message)
//
//    class LOADING<T>: UIState<T>()
//}