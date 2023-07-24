package com.example.movie2free.domain.di

import com.example.movie2free.domain.usecase.MovieUseCase
import com.example.movie2free.domain.usecase.MovieUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<MovieUseCase> {MovieUseCaseImpl(get())}
}