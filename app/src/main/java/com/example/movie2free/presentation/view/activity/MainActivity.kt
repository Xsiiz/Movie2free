package com.example.movie2free.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.movie2free.databinding.ActivityMainBinding
import com.example.movie2free.presentation.viewModel.MovieViewModel
import com.example.movie2free.utils.AppUtils.MOVIE_CASE
import com.example.movie2free.utils.AppUtils.TV_SERIES_CASE
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupView()
        setContentView(binding.root)
    }

    private fun setupView() {
        val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
        movieViewModel.movieSelectedData.observe(this) { movieSelectedData ->
            lifecycleScope.launchWhenResumed {
                intent.putExtra("movieSelectedData", movieSelectedData)
                intent.putExtra("case", MOVIE_CASE)
                startActivity(intent)
                Log.d("movieSelectedPosition", movieSelectedData.toString()) }
        }
        movieViewModel.tvSeriesSelectedData.observe(this) { tvSeriesSelectedData ->
            lifecycleScope.launchWhenResumed {
                intent.putExtra("tvSeriesSelectedData", tvSeriesSelectedData)
                intent.putExtra("case", TV_SERIES_CASE)
                startActivity(intent)
            }
        }
//        intent.putExtra("movieSelectedPosition",movieViewModel.movieSelectedPosition.value)
//        startActivity(intent)
    }
}