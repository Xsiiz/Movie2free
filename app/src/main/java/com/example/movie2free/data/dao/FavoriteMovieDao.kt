package com.example.movie2free.data.dao

import androidx.room.*
import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData
import com.example.movie2free.data.model.favorite_movie.TABLE_NAME
import com.example.movie2free.data.model.movie.MovieResult

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoriteMovieData: FavoriteMovieData): Long

    @Query("SELECT * FROM $TABLE_NAME")
    fun getFavoriteMovie(): List<FavoriteMovieData>

    @Query("SELECT * FROM $TABLE_NAME WHERE id == :id")
    fun getFavoriteMovieByID(id: Int?): Int

    @Delete
    fun deleteFavoriteMovie(favoriteMovieData: FavoriteMovieData)

}