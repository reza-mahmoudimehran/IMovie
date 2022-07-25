package ir.reza_mahmoudi.imovie.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("Search")
    val search: MutableList<MovieItem>,
    @SerializedName("Response")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)