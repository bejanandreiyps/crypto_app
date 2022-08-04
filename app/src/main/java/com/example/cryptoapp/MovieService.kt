package com.example.cryptoapp

import com.example.cryptoapp.domain.gallery.GalleryModel
import com.example.cryptoapp.domain.gallery.MovieOrSeriesModel
import com.example.cryptoapp.domain.login.CredentialsModel
import com.example.cryptoapp.domain.login.TokenModel
import com.example.cryptoapp.domain.stars.MovieStarModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {
    @GET("/3/authentication/token/new")
    suspend fun getToken(
        @Query("api_key") api_key: String,
    ): TokenModel

    @POST("/3/authentication/token/validate_with_login")
    suspend fun postLogin(
        @Query("api_key") api_key: String,
        @Body credentials : CredentialsModel
    ): TokenModel

    @GET("/3/trending/all/day")
    suspend fun getGalleryMoviesOrSeries(
        @Query("api_key") api_key: String
    ): MovieOrSeriesModel

    @GET("3/person/popular")
    suspend fun getMovieStars(
        @Query("api_key") api_key: String
    ) : MovieStarModel
}