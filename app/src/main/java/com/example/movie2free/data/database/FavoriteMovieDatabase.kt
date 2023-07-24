package com.example.movie2free.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie2free.data.dao.FavoriteMovieDao
import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData

const val DB_NAME = "favorite_movie_database"

@Database(entities = [FavoriteMovieData::class], version = 3, exportSchema = false)

abstract class FavoriteMovieDatabase: RoomDatabase() { //

    abstract fun getFavoriteMovieDao(): FavoriteMovieDao

    companion object { // singleton
        @Volatile // เห็น class ได้ทุกเทรด
        private var INSTANCE: FavoriteMovieDatabase? = null

        fun getInstance(context: Context): FavoriteMovieDatabase {
            return INSTANCE ?: synchronized(FavoriteMovieDatabase@this) { // lock การทำงานไม่ให้ชนกัน
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteMovieDatabase::class.java,
                    DB_NAME // build instance
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance // return
            }
        }
    }

}