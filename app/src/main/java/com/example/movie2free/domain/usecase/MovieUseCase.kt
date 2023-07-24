package com.example.movie2free.domain.usecase

import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.tvseries.TvSeriesData
import retrofit2.Response

interface MovieUseCase {
    suspend fun getPopularMovies(): MovieData?
    suspend fun getPopularTvSeries(): TvSeriesData?
}