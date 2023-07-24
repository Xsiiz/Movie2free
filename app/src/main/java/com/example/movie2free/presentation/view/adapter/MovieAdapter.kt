package com.example.movie2free.presentation.view.adapter

import android.app.Application
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.movie2free.data.model.Item
import com.example.movie2free.data.model.movie.MovieData
import com.example.movie2free.data.model.movie.MovieResult
import com.example.movie2free.databinding.ItemAdBinding
import com.example.movie2free.databinding.ItemMovieCardBinding
import com.example.movie2free.utils.AppUtils.ITEM_AD
import com.example.movie2free.utils.AppUtils.ITEM_MOVIE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.koinApplication

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //    private var item: ArrayList<Item?> = arrayListOf()
    private val movieDataList: ArrayList<MovieResult?> = arrayListOf()
    private lateinit var listener: MovieSelectAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemMovieCardBinding.inflate(layoutInflater, parent, false)
        return when (viewType) {
            ITEM_AD -> AdViewHolder(
                ItemAdBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> {
                MovieViewHolder(
                    ItemMovieCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdViewHolder -> holder.bindView()
            is MovieViewHolder -> holder.bindView(position)
        }
    }

    override fun getItemCount(): Int {
        return movieDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 7 == 6) {
            ITEM_AD
        } else ITEM_MOVIE
    }

    fun setData(movieData: MovieData) {
        movieData.movieResults?.let { results ->
            movieDataList.clear()
            movieDataList.addAll(movieData.movieResults ?: arrayListOf())
            notifyDataSetChanged()
        }
    }

    fun setMovieSelectAdapterListener(movieSelectAdapterListener: MovieSelectAdapterListener) {
        this@MovieAdapter.listener = movieSelectAdapterListener
    }

    interface MovieSelectAdapterListener {
        fun onSelect(MovieSelectedData: MovieResult?)
    }

    inner class MovieViewHolder(private val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.moviePicture.load(movieDataList[position]?.posterPath) {
                listener(object : ImageRequest.Listener {
                    override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                        super.onSuccess(request, result)
                        hideShimmerView()
                    }

                    override fun onError(request: ImageRequest, result: ErrorResult) {
                        super.onError(request, result)
                        hideShimmerView()
                    }

                    override fun onCancel(request: ImageRequest) {
                        super.onCancel(request)
                        hideShimmerView()
                    }
                })
            }
            binding.movieText.text = movieDataList[position]?.title

            binding.movieCardView.setOnClickListener {
                listener.onSelect(movieDataList[position])
            }
        }

        private fun hideShimmerView() {
            CoroutineScope(Dispatchers.Main).launch {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
            }
        }
    }

    inner class AdViewHolder(private val binding: ItemAdBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView() {
            binding.imageView2.load("https://i.pinimg.com/originals/61/5c/95/615c953f975ed731fc0864f07ba3a593.jpg")
        }
    }
}