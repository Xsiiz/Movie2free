package com.example.movie2free.data.di

import com.example.movie2free.data.dao.FavoriteMovieDao
import com.example.movie2free.data.database.FavoriteMovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<FavoriteMovieDatabase> { FavoriteMovieDatabase.getInstance(androidApplication()) }
    factory<FavoriteMovieDao> { get<FavoriteMovieDatabase>().getFavoriteMovieDao() }
}