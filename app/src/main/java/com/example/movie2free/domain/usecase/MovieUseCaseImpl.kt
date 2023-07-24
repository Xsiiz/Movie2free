package com.example.movie2free.domain.usecase


import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.tvseries.TvSeriesData
import com.example.movie2free.domain.repository.MovieRepository
import com.example.movie2free.utils.AppUtils.TMDB_IMAGE_BASE_URL

class MovieUseCaseImpl(private val movieRepository: MovieRepository) :  MovieUseCase{
    override suspend fun getPopularMovies(): MovieData? {
        val response = movieRepository.getPopularMovies()
        return if(response.isSuccessful){
            val body = response.body()
            body?.movieResults?.forEach { result ->
                result?.posterPath = TMDB_IMAGE_BASE_URL + result?.posterPath
                result?.backdropPath = TMDB_IMAGE_BASE_URL + result?.backdropPath
            }
            body
        } else null
    }

    override suspend fun getPopularTvSeries(): TvSeriesData? {
        val response = movieRepository.getPopularTvSeries()
        return if (response.isSuccessful){
            val body = response.body()
            body?.tvSeriesResults?.forEach{ result ->
                result?.posterPath = TMDB_IMAGE_BASE_URL + result?.posterPath
                result?.backdropPath = TMDB_IMAGE_BASE_URL + result?.backdropPath
            }
            body
        } else null
    }
}