package com.example.movie2free.domain.di

import com.example.movie2free.data.repositoryImpl.FavoriteMovieRepositoryImpl
import com.example.movie2free.data.repositoryImpl.MovieRepositoryImpl
import com.example.movie2free.domain.repository.FavoriteMovieRepository
import com.example.movie2free.domain.repository.MovieRepository
import com.example.movie2free.utils.AppUtils.API_KEY
import org.koin.dsl.module


val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryImpl(get(), API_KEY)}
    factory<FavoriteMovieRepository> { FavoriteMovieRepositoryImpl(get()) }
}