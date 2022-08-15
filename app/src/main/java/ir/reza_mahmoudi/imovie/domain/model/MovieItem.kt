package ir.reza_mahmoudi.imovie.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "movie_item"
)
data class MovieItem(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @PrimaryKey()
    @SerializedName("imdbID")
    val imdbID: String="",
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Poster")
    val poster: String?)