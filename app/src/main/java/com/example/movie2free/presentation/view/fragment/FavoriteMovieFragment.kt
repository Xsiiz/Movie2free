package com.example.movie2free.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData
import com.example.movie2free.databinding.FragmentFavoriteMovieBinding
import com.example.movie2free.presentation.view.adapter.FavoriteMovieAdapter
import com.example.movie2free.presentation.viewModel.MovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FavoriteMovieFragment :  Fragment() {

    private var favoriteMovieList: ArrayList<FavoriteMovieData> = arrayListOf()
    private lateinit var binding: FragmentFavoriteMovieBinding
    private val movieViewModel: MovieViewModel by sharedViewModel()
    private var favoriteMovieAdapter = FavoriteMovieAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            movieViewModel.getFavoriteMovie()
//            binding.swipeRefreshLayout.isRefreshing = false
//        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.getFavoriteMovie()
        observeLiveData()
    }

    private fun setupView() {
        favoriteMovieAdapter.setData(favoriteMovieList)
        binding.favoriteMovieRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteMovieRecycleView.adapter = favoriteMovieAdapter
    }

    private fun observeLiveData() {
        movieViewModel.favoriteMovieLiveData.observe(viewLifecycleOwner) { favoriteMovie ->
            favoriteMovieList.clear()
            favoriteMovieList.addAll(favoriteMovie)
            Log.d("fav_movie",favoriteMovieList.toString())
            setupView()
        }
    }
}