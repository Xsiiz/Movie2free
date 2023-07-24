package com.example.movie2free.data.model.movie


import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var movieResults: List<MovieResult?>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)