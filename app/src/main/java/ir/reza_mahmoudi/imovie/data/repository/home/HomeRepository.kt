package ir.reza_mahmoudi.imovie.data.repository.home

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ir.reza_mahmoudi.imovie.model.MovieDetails
import ir.reza_mahmoudi.imovie.model.SearchResponse

interface HomeRepository {
    // Remote
    fun getMovies(searchText:String): Single<SearchResponse>

    // Local
    fun getAllSearchCase(): Single<List<SearchResponse>>
    fun insertSearchCase(movieDetails: SearchResponse): Single<Long>
    fun getSearchCase(searchText: String): Single<SearchResponse>
}