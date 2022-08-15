package ir.reza_mahmoudi.imovie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "search_response"
)
data class SearchResponse(
    @SerializedName("Search")
    val searchList: List<MovieItem>?,
    @PrimaryKey()
    var searchText: String,
    @SerializedName("Response")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)