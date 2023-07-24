package com.example.movie2free.domain.repository

import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.tvseries.TvSeriesData
import retrofit2.Response


interface MovieRepository {
    suspend fun getPopularMovies(): Response<MovieData>
    suspend fun getPopularTvSeries(): Response<TvSeriesData>
}