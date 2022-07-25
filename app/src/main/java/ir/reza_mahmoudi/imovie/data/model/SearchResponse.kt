package ir.reza_mahmoudi.imovie.data.model

data class SearchResponse(
    val Search: MutableList<MovieItem>,
    val Response: String,
    val totalResults: Int
)