package com.example.movie2free.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie2free.data.model.tvseries.TvSeriesResult
import com.example.movie2free.databinding.FragmentTvSeriesBinding
import com.example.movie2free.presentation.view.adapter.TvSeriesAdapter
import com.example.movie2free.presentation.viewModel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TvSeriesFragment: Fragment() {

    private val tvSeriesAdapter = TvSeriesAdapter()
    private val movieViewModel: MovieViewModel by sharedViewModel()
    private lateinit var binding: FragmentTvSeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Run", "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        setupView()
        observeLiveData()
        setupViewModel()
        return binding.root
        Log.d("Run", "OnCreateView")
    }

    private fun setupView() {
        tvSeriesAdapter.setTvSeriesAdapterListener(object : TvSeriesAdapter.TvSeriesAdapterListener{
            override fun onSelect(tvSeriesSelectedData: TvSeriesResult? ) {
                movieViewModel.getTvSeriesSelectedData(tvSeriesSelectedData)
            }
        })
        binding.tvSeriesRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        binding.tvSeriesRecyclerView.adapter = tvSeriesAdapter
    }

    private fun setupViewModel() {
        movieViewModel.getPopularTvSeries()
    }

    private fun observeLiveData() {
        movieViewModel.tvSeriesLiveData.observe(viewLifecycleOwner){ tvSeriesData ->
            tvSeriesData?.let {
                tvSeriesAdapter.setData(it)
            }
            Log.d("movie2free",tvSeriesData.tvSeriesResults?.get(0)?.name.toString())
        }
    }
}