package com.example.acronymapp.data.model


import com.squareup.moshi.Json

data class AcronymResponseItem(
    @Json(name = "lfs")
    val lfs: List<Lf?>? = null,
    @Json(name = "sf")
    val sf: String? = null
)