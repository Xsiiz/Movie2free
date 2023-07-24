package com.example.movie2free.data.repositoryImpl

import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.tvseries.TvSeriesData
import com.example.movie2free.data.service.TMDBService
import com.example.movie2free.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepositoryImpl(private val tmbdService :TMDBService, private val APIKey: String)
    : MovieRepository{

    override suspend fun getPopularMovies(): Response<MovieData> {
        return withContext(Dispatchers.IO){
            tmbdService.getPopularMovies(APIKey)
        }
    }

    override suspend fun getPopularTvSeries(): Response<TvSeriesData> {
        return withContext(Dispatchers.IO){
            tmbdService.getPopularTvSeries(APIKey)
        }
    }


}
