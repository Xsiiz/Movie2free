package com.example.movie2free.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie2free.presentation.view.fragment.FavoriteMovieFragment
import com.example.movie2free.presentation.view.fragment.MovieFragment
import com.example.movie2free.presentation.view.fragment.TvSeriesFragment

class MainPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragments = arrayListOf(
        MovieFragment(),
        TvSeriesFragment(),
        FavoriteMovieFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}