package ir.reza_mahmoudi.imovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("imdbID")
    val imdbID: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Poster")
    val poster: String?)