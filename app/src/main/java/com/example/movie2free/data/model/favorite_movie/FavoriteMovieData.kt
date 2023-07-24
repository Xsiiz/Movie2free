package com.example.movie2free.data.model.favorite_movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME ="favorite_movie_table"

@Entity(tableName = TABLE_NAME)
data class FavoriteMovieData(
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    @ColumnInfo(name = "over_view")
    var overView: String
)
