package ir.reza_mahmoudi.imovie.data.repository.details

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.model.MovieDetails

class DetailsRepositoryImpl : DetailsRepository{
    override fun getMovieDetails(imdbID: String): Single<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun getAllDetails(): Single<List<MovieDetails>> {
        TODO("Not yet implemented")
    }

    override fun insertDetails(movieDetails: MovieDetails): Single<Long> {
        TODO("Not yet implemented")
    }

    override fun getDetails(detailsImdbID: String): Single<MovieDetails> {
        TODO("Not yet implemented")
    }
}