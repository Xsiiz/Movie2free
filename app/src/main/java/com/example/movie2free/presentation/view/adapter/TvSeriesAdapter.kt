package com.example.movie2free.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.movie2free.data.model.tvseries.TvSeriesData
import com.example.movie2free.data.model.tvseries.TvSeriesResult
import com.example.movie2free.databinding.ItemMovieCardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvSeriesAdapter : RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>() {

    private val tvSeriesDataList: ArrayList<TvSeriesResult?> = arrayListOf()
    private lateinit var listener: TvSeriesAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieCardBinding.inflate(layoutInflater, parent, false)
        return TvSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return tvSeriesDataList.size
    }

    fun setData(tvSeriesData: TvSeriesData) {
        tvSeriesData.tvSeriesResults?.let { results ->
            tvSeriesDataList.clear()
            tvSeriesDataList.addAll(tvSeriesData.tvSeriesResults ?: arrayListOf())
            notifyDataSetChanged()
        }
    }

    fun setTvSeriesAdapterListener(tvSeriesSelectAdapterListener: TvSeriesAdapterListener) {
        this@TvSeriesAdapter.listener = tvSeriesSelectAdapterListener
    }

    interface TvSeriesAdapterListener {
        fun onSelect(TvSeriesSelectedData: TvSeriesResult?)
    }

    inner class TvSeriesViewHolder(private val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.moviePicture.load(tvSeriesDataList[position]?.posterPath) {
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
            binding.movieText.text = tvSeriesDataList[position]?.name

            binding.movieCardView.setOnClickListener {
                listener.onSelect(tvSeriesDataList[position])
            }
        }

        private fun hideShimmerView() {
            CoroutineScope(Dispatchers.Main).launch {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
            }
        }
    }
}