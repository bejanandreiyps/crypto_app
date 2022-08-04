package com.example.cryptoapp.domain.stars

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieStarModel (
    val page: Int = 0,
    val results: List<ActorModel> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
) {}