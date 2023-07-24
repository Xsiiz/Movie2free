package com.example.movie2free.presentation.view.fragment

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.load
import com.example.movie2free.R
import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.movie.MovieResult
import com.example.movie2free.data.model.tvseries.TvSeriesResult
import com.example.movie2free.databinding.FragmentMovieDetailsBinding
import com.example.movie2free.presentation.viewModel.MovieViewModel
import com.example.movie2free.presentation.viewModel.NON_SELECTION
import com.example.movie2free.presentation.viewModel.SELECTION
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment: Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieViewModel: MovieViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Run", "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        observeLiveData()
        return binding.root
        Log.d("Run", "OnCreateView")
    }

    private fun setupToolbar() {
        binding.movieDetailsToolbar.inflateMenu(R.menu.menu)
        binding.movieDetailsToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)
        binding.movieDetailsToolbar.setNavigationOnClickListener {
            activity?.finish()
        }
    }

    private fun setupMovieView(movieSelectedData: MovieResult?) {
        setupToolbar()

        binding.movieDetailsToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.favorite_button -> {
                    movieViewModel.checkFavoriteMovie(movieSelectedData)
                    if (movieViewModel.buttonStatus  == NON_SELECTION) {
                        binding.movieDetailsToolbar.menu.findItem(R.id.favorite_button)
                            .setIcon(R.drawable.ic_baseline_favorite_24)
                    }
                    else if (movieViewModel.buttonStatus  == SELECTION){
                        binding.movieDetailsToolbar.menu.findItem(R.id.favorite_button)
                            .setIcon(R.drawable.ic_baseline_favorite_border_24)
                    }
                }
            }
            true
        }
        binding.tvMovieTitle.text = movieSelectedData?.title
        binding.imvMovieBackdrop.load(movieSelectedData?.backdropPath)
        binding.imvMoviePoster.load(movieSelectedData?.posterPath)
        binding.tvReleaseDate.text = movieSelectedData?.releaseDate
        binding.tvReleaseDate2.text = movieSelectedData?.overview
        binding.ratingBar.rating = movieSelectedData?.voteAverage?.toFloat()!! / 2
        binding.tvRating.text = movieSelectedData?.voteAverage.toString()
    }

    private fun setupTvSeriesView(tvSeriesSelectedData: TvSeriesResult?) {
        setupToolbar()
        binding.tvMovieTitle.text = tvSeriesSelectedData?.name
        binding.imvMovieBackdrop.load(tvSeriesSelectedData?.backdropPath)
        binding.imvMoviePoster.load(tvSeriesSelectedData?.posterPath)
        binding.tvReleaseDate.text = tvSeriesSelectedData?.firstAirDate
        binding.tvReleaseDate2.text = tvSeriesSelectedData?.overview
        binding.ratingBar.rating = tvSeriesSelectedData?.voteAverage?.toFloat()!! / 2
        binding.tvRating.text = tvSeriesSelectedData?.voteAverage.toString()
    }

    private fun observeLiveData() {
        movieViewModel.movieSelectedData.observe(viewLifecycleOwner){ movieSelectedData ->
            setupMovieView(movieSelectedData)
        }

        movieViewModel.tvSeriesSelectedData.observe(viewLifecycleOwner){ tvSeriesSelectedData ->
            setupTvSeriesView(tvSeriesSelectedData)
        }
    }

}