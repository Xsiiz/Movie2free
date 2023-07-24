package com.example.movie2free.data.di

import com.example.movie2free.data.service.TMDBService
import com.example.movie2free.utils.AppUtils.TMDB_BASE_URL
import com.google.gson.Gson
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkingModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single<TMDBService> { get<Retrofit>().create(TMDBService::class.java) }
}

private fun provideOkHttpClient() =
    OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    )
        .connectTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(30,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
