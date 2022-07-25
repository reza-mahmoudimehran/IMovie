package ir.reza_mahmoudi.imovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("Plot")
    val summary: String?,
    @SerializedName("Genre")
    val genre: String?,
    @SerializedName("Runtime")
    val time: String?,
    @SerializedName("Metascore")
    val metaScore: String?,
    @SerializedName("imdbRating")
    val imdbRating: String?,
    @SerializedName("Poster")
    val poster: String?)