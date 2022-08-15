package ir.reza_mahmoudi.imovie.data.repository.details

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.domain.model.MovieDetails

interface DetailsRepository {
    // Remote
    fun getMovieDetails(imdbID:String): Single<MovieDetails>

    // Local
    fun getAllDetails(): Single<List<MovieDetails>>
    fun insertDetails(movieDetails: MovieDetails): Single<Long>
    fun getDetails(detailsImdbID: String): Single<MovieDetails>
}