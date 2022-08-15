package ir.reza_mahmoudi.imovie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "movie_details"
)
data class MovieDetails(
    @PrimaryKey()
    @SerializedName("imdbID")
    val imdbID: String ="",
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