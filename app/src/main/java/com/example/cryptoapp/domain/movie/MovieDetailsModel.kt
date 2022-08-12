package com.example.cryptoapp.domain.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class MovieDetailsModel(
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("genre_ids")
    val genreIDs: List<Int> = emptyList(),
    val id: Int = 0,
    @SerialName("original_name")
    val originalName: String = "",
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = emptyList(),
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("first_air_date")
    val firstAirDate: String = "",
    val name: String = "",
    val isFavorite: Boolean = false
) {}