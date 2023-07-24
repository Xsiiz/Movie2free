package com.example.movie2free.presentation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movie2free.data.model.movie.MovieResult
import com.example.movie2free.data.model.tvseries.TvSeriesResult
import com.example.movie2free.databinding.ActivityDetailsBinding
import com.example.movie2free.presentation.viewModel.MovieViewModel
import com.example.movie2free.utils.AppUtils.MOVIE_CASE
import com.example.movie2free.utils.AppUtils.TV_SERIES_CASE
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var navController: NavController
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setupView()
        setContentView(binding.root)
    }

    private fun setupView() {
        var case = intent.getIntExtra("case", 0)
        when (case) {
            MOVIE_CASE -> {
                var movieSelectedData = intent.getSerializableExtra("movieSelectedData")
                movieViewModel.getMovieSelectedData(movieSelectedData as MovieResult?)
            }
            TV_SERIES_CASE -> {
                var tvSeriesSelectedData = intent.getSerializableExtra("tvSeriesSelectedData")
                movieViewModel.getTvSeriesSelectedData(tvSeriesSelectedData as TvSeriesResult?)
            }
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
    }
}