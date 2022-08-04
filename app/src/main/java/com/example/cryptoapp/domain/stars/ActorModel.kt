package com.example.cryptoapp.domain.stars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorModel(
    val adult: Boolean = false,
    val gender: Int = 0,
    val id: Int = 0,
    @SerialName("known_for")
    val knownFor: List<FamousMovieModel> = emptyList(),
    val name: String = "",
    @SerialName("known_for_department")
    val knownForDepartment: String = "",
    val popularity: Double = 0.0,
    @SerialName("profile_path")
    val profilePath: String = ""
) {}