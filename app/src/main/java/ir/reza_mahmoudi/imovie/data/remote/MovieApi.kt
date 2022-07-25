package ir.reza_mahmoudi.imovie.data.remote

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.data.model.SearchResponse
import ir.reza_mahmoudi.imovie.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    fun getMovies(
        @Query("s") searchText:String,
        @Query("apiKey")
        apiKey: String = API_KEY): Single<SearchResponse>
}