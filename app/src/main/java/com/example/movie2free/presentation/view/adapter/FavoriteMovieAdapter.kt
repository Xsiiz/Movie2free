package com.example.movie2free.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movie2free.data.model.favorite_movie.FavoriteMovieData
import com.example.movie2free.databinding.ItemFavoriteMovieCardBinding
import com.example.movie2free.databinding.ItemMovieCardBinding
import kotlinx.coroutines.delay


class FavoriteMovieAdapter: RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    private var favoriteMovieList: ArrayList<FavoriteMovieData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieAdapter.FavoriteMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteMovieCardBinding.inflate(layoutInflater, parent, false)
        return FavoriteMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieAdapter.FavoriteMovieViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return favoriteMovieList.size
    }

    fun setData(favoriteMovieDataList : ArrayList<FavoriteMovieData>) {
        this@FavoriteMovieAdapter.favoriteMovieList = favoriteMovieDataList
        notifyDataSetChanged()
    }

    inner class FavoriteMovieViewHolder(private val binding: ItemFavoriteMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(position: Int) {
            binding.tvMovieTitle.text = favoriteMovieList[position].name
            binding.tvReleaseDate.text = favoriteMovieList[position].releaseDate
            binding.tvOverview.text = favoriteMovieList[position].overView
            binding.imvMoviePicture.load(favoriteMovieList[position].posterPath)
        }
    }
}