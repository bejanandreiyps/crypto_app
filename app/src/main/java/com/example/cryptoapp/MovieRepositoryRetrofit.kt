package com.example.cryptoapp

import com.example.cryptoapp.domain.gallery.MovieOrSeriesModel
import com.example.cryptoapp.domain.login.CredentialsModel
import com.example.cryptoapp.domain.login.TokenModel
import com.example.cryptoapp.domain.stars.MovieStarModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object MovieRepositoryRetrofit {

    private const val apiKey: String = "96d31308896f028f63b8801331250f03"

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val service = retrofit.create(MovieService::class.java)

    suspend fun getToken(): TokenModel {
        return service.getToken(apiKey)
    }

    suspend fun postLogin(credentials: CredentialsModel): TokenModel {
        return service.postLogin(apiKey, credentials)
    }

    suspend fun getGalleryMoviesOrSeries(): MovieOrSeriesModel {
        return service.getGalleryMoviesOrSeries(apiKey)
    }

    suspend fun getMovieStars() : MovieStarModel {
        return service.getMovieStars(apiKey)
    }
}