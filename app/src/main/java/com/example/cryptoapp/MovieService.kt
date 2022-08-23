package com.example.cryptoapp

import com.example.cryptoapp.domain.gallery.MovieOrSeriesModel
import com.example.cryptoapp.domain.login.CredentialsModel
import com.example.cryptoapp.domain.login.TokenModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.stars.MovieStarModel
import com.example.cryptoapp.domain.movie.MovieModel
import com.example.cryptoapp.domain.stars.ActorModel
import retrofit2.http.*

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
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieStarModel

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieModel

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieModel

    @GET("/3/tv/airing_today")
    suspend fun getAiringTodayMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieModel

    @GET("3/movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetailsModel

    @GET("3/movie/{movie_id}")
    suspend fun getActors(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): ActorModel


    @GET("/3/search/movie")
    suspend fun getSearch(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ) : MovieModel
}