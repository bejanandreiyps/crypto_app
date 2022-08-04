package com.example.cryptoapp.domain.stars

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FamousMovieModel(
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genreIDs: List<Int> = emptyList(),
    val id: Int = 0,
    @SerialName("media_type")
    val mediaType: String = "",
    val name: String = "",
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    val overview: String = "",
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Double = 0.0,
    @SerialName("first_air_date")
    val firstAirDate: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = emptyList()
) {}