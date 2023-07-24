package com.example.movie2free.domain.repository

import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData
import com.example.movie2free.data.model.movie.MovieResult

interface FavoriteMovieRepository {

    suspend fun insertFavoriteMovie(favoriteMovieData: FavoriteMovieData): Long
    suspend fun getFavoriteMovie(): List<FavoriteMovieData>
    suspend fun getFavoriteMovieByID(id: Int?): Int?
    suspend fun deleteFavoriteMovie(favoriteMovieData: FavoriteMovieData)

}