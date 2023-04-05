package com.example.acronymapp.data.model


import com.squareup.moshi.Json

data class Var(
    @Json(name = "freq")
    val freq: Int? = null,
    @Json(name = "lf")
    val lf: String? = null,
    @Json(name = "since")
    val since: Int? = null
)