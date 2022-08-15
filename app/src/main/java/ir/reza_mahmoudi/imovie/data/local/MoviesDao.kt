package ir.reza_mahmoudi.imovie.data.local

import androidx.room.*
import io.reactivex.Single
import ir.reza_mahmoudi.imovie.domain.model.MovieDetails
import ir.reza_mahmoudi.imovie.domain.model.SearchResponse

@Dao
interface MoviesDao {

    //Search Case Queries
    @Query("SELECT * FROM search_response")
    fun getAllSearchCase(): Single<List<SearchResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchCase(movieDetails: SearchResponse): Single<Long>

    @Query("SELECT * FROM search_response where searchText= :searchText")
    fun getSearchCase(searchText: String): Single<SearchResponse>

    //Movie Details Queries
    @Query("SELECT * FROM movie_details")
    fun getAllDetails(): Single<List<MovieDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(movieDetails: MovieDetails): Single<Long>

    @Query("SELECT * FROM movie_details where imdbID= :detailsImdbID")
    fun getDetails(detailsImdbID: String): Single<MovieDetails>
}