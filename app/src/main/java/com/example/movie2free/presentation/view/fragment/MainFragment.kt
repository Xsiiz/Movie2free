package com.example.movie2free.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movie2free.R
import com.example.movie2free.databinding.FragmentMainBinding
import com.example.movie2free.presentation.view.adapter.MainPagerAdapter
import com.example.movie2free.presentation.viewModel.MovieViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
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
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
        Log.d("Run", "OnCreateView")
    }

    private fun setupView() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
        binding.viewPager.adapter = MainPagerAdapter(this@MainFragment)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0 -> {
                    tab.text = getString(R.string.movie)
                }
                1 -> {
                    tab.text = getString(R.string.tv_series)
                }
                2 -> {
                    tab.text = getString(R.string.favorite_movie)
                }
            }
        }.attach()
    }


}