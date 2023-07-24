package com.example.movie2free.data.service

import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.tvseries.TvSeriesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") APIkey: String): Response<MovieData>

    @GET("tv/popular")
    suspend fun getPopularTvSeries(@Query("api_key") APIkey: String): Response<TvSeriesData>


}