package com.example.movie2free.presentation.view.di

import com.example.movie2free.presentation.viewModel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel() { MovieViewModel(get(),get()) }
}