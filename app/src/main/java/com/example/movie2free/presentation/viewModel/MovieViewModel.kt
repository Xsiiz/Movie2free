package com.example.movie2free.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData
import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.movie.MovieResult
import com.example.movie2free.data.model.tvseries.TvSeriesData
import com.example.movie2free.data.model.tvseries.TvSeriesResult
import com.example.movie2free.domain.repository.FavoriteMovieRepository
import com.example.movie2free.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val SELECTION = 0
const val NON_SELECTION = 1

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
    private val favoriteMovieRepository: FavoriteMovieRepository
) : ViewModel() {

//    private val _movieSelectedPosition: MutableLiveData<Int> = MutableLiveData()
//    val movieSelectedPosition: LiveData<Int> = _movieSelectedPosition

    private val _movieLiveData: MutableLiveData<MovieData> = MutableLiveData()
    val movieLiveData: LiveData<MovieData> = _movieLiveData

    private val _tvSeriesLiveData: MutableLiveData<TvSeriesData> = MutableLiveData()
    val tvSeriesLiveData: LiveData<TvSeriesData> = _tvSeriesLiveData

    private val _movieSelectedData: MutableLiveData<MovieResult?> = MutableLiveData()
    val movieSelectedData: LiveData<MovieResult?> = _movieSelectedData

    private val _tvSeriesSelectedData: MutableLiveData<TvSeriesResult?> = MutableLiveData()
    val tvSeriesSelectedData: LiveData<TvSeriesResult?> = _tvSeriesSelectedData

    private val _favoriteMovieLiveData: MutableLiveData<List<FavoriteMovieData>> = MutableLiveData()
    val favoriteMovieLiveData: LiveData<List<FavoriteMovieData>> = _favoriteMovieLiveData

    var buttonStatus: Int? = NON_SELECTION


    fun getPopularMovie() {
        viewModelScope.launch(Dispatchers.Main)  {
            movieUseCase.getPopularMovies()?.let {
                //success
                _movieLiveData.postValue(it)
            } ?: run {
                //show error
            }
        }
    }

    fun getPopularTvSeries() {
        viewModelScope.launch(Dispatchers.Main)  {
            movieUseCase.getPopularTvSeries()?.let {
                //success
                _tvSeriesLiveData.postValue(it)
            } ?: run {
                //show error
            }
        }
    }

//    fun getMovieSelectedPosition(movieSelectedPosition: Int) {
//        _movieSelectedPosition.value = movieSelectedPosition
//        Log.d("movieSelectedPosition", _movieSelectedPosition.value.toString())
//    }

    fun getMovieSelectedData(movieSelectedData: MovieResult?) {
        viewModelScope.launch(Dispatchers.Main)  {
            _movieSelectedData.postValue(movieSelectedData)
        }
    }

    fun getTvSeriesSelectedData(tvSeriesSelectedData: TvSeriesResult?) {
        viewModelScope.launch(Dispatchers.Main)  {
            _tvSeriesSelectedData.postValue(tvSeriesSelectedData)
        }
    }

    fun checkFavoriteMovie(favoriteMovieData: MovieResult?) {
        viewModelScope.launch(Dispatchers.Main)  {
            var movieID = favoriteMovieRepository.getFavoriteMovieByID(favoriteMovieData?.id)
            var favoriteMovie = FavoriteMovieData(
                id = favoriteMovieData?.id,
                name = favoriteMovieData?.title,
                posterPath = favoriteMovieData?.posterPath.toString(),
                releaseDate = favoriteMovieData?.releaseDate.toString(),
                overView = favoriteMovieData?.overview.toString()
            )
            if (favoriteMovie?.id == movieID) {
                deleteFavoriteMovie(favoriteMovie)
            } else insertFavoriteMovie(favoriteMovie)
        }
    }

    private fun insertFavoriteMovie(favoriteMovieData: FavoriteMovieData) {
        viewModelScope.launch(Dispatchers.Main)  {
            favoriteMovieRepository.insertFavoriteMovie(favoriteMovieData)
            buttonStatus = SELECTION
            getFavoriteMovie()
        }
    }

    private fun deleteFavoriteMovie(favoriteMovieData: FavoriteMovieData) {
        viewModelScope.launch(Dispatchers.Main)  {
            favoriteMovieRepository.deleteFavoriteMovie(favoriteMovieData)
            buttonStatus = NON_SELECTION
            getFavoriteMovie()
        }
    }

    fun getFavoriteMovie(){
        viewModelScope.launch(Dispatchers.Main)  {
            val favoriteMovieList = favoriteMovieRepository.getFavoriteMovie()
            favoriteMovieList.let {
                Log.d("fav_movie", favoriteMovieList.toString())
                _favoriteMovieLiveData.value = it
            }
        }
    }

}