package com.example.movie2free.data.repositoryImpl

import com.example.movie2free.data.dao.FavoriteMovieDao
import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData
import com.example.movie2free.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteMovieRepositoryImpl(private val favoriteMovieDao: FavoriteMovieDao): FavoriteMovieRepository {
    override suspend fun insertFavoriteMovie(favoriteMovieData: FavoriteMovieData): Long {
        return withContext(Dispatchers.IO){
            favoriteMovieDao.insertFavoriteMovie(favoriteMovieData)
        }
    }

    override suspend fun getFavoriteMovie(): List<FavoriteMovieData> {
        return withContext(Dispatchers.IO){
            favoriteMovieDao.getFavoriteMovie()
        }
    }

    override suspend fun getFavoriteMovieByID(id: Int?): Int? {
        return withContext(Dispatchers.IO){
            id?.let { favoriteMovieDao.getFavoriteMovieByID(it) }
        }
    }

    override suspend fun deleteFavoriteMovie(favoriteMovieData: FavoriteMovieData) {
        return withContext(Dispatchers.IO){
            favoriteMovieDao.deleteFavoriteMovie(favoriteMovieData)
        }
    }
}