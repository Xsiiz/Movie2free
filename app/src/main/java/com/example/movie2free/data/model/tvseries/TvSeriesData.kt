package com.example.movie2free.data.model.tvseries


import com.google.gson.annotations.SerializedName

data class TvSeriesData(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var tvSeriesResults: List<TvSeriesResult?>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)