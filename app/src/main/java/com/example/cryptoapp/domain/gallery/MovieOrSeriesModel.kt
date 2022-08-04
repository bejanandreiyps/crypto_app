package com.example.cryptoapp.domain.gallery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieOrSeriesModel (
    val page: Int = 0,
    val results: List<ResultModel>,
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
) {}