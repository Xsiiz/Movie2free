package com.example.movie2free.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie2free.data.model.movie.MovieResult
import com.example.movie2free.databinding.FragmentMovieBinding
import com.example.movie2free.presentation.view.adapter.MovieAdapter
import com.example.movie2free.presentation.viewModel.MovieViewModel
import com.example.movie2free.utils.AppUtils.ITEM_AD
import com.example.movie2free.utils.AppUtils.ITEM_MOVIE
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieFragment: Fragment() {

    private val movieAdapter = MovieAdapter()
    private val movieViewModel: MovieViewModel by sharedViewModel()
    //private lateinit var activityMain: MainActivity
    private lateinit var binding: FragmentMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Run", "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        setupView()
        observeLiveData()
        setupViewModel()
        return binding.root
        Log.d("Run1", "OnCreateView")
    }

    private fun setupView() {
        var gridLayoutManager = GridLayoutManager(requireContext(), 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when (movieAdapter.getItemViewType(position)){
                    ITEM_AD -> 3
                    ITEM_MOVIE -> 1
                    else -> 0
                }
            }
        }
        movieAdapter.setMovieSelectAdapterListener(object : MovieAdapter.MovieSelectAdapterListener{
            override fun onSelect(movieSelectedData: MovieResult?) {
                movieViewModel.getMovieSelectedData(movieSelectedData)
            }
        })
        binding.movieRecycleView.layoutManager = gridLayoutManager
        binding.movieRecycleView.adapter = movieAdapter
    }

    private fun setupViewModel() {
        movieViewModel.getPopularMovie()
    }

    private fun observeLiveData() {
        movieViewModel.movieLiveData.observe(viewLifecycleOwner){ movieData ->
            movieData?.let {
                movieAdapter.setData(it)
            }
            Log.d("movie2free",movieData.movieResults?.get(0)?.title.toString())
        }
    }

}