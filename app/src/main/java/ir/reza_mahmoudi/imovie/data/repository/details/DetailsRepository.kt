package ir.reza_mahmoudi.imovie.data.repository.details

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ir.reza_mahmoudi.imovie.model.MovieDetails
import ir.reza_mahmoudi.imovie.model.SearchResponse

interface DetailsRepository {
    // Remote
    fun getMovieDetails(imdbID:String): Single<MovieDetails>

    // Local
    fun getAllDetails(): Single<List<MovieDetails>>
    fun insertDetails(movieDetails: MovieDetails): Single<Long>
    fun getDetails(detailsImdbID: String): Single<MovieDetails>
}