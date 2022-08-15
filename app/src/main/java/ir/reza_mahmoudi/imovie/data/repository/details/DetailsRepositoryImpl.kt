package ir.reza_mahmoudi.imovie.data.repository.details

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.data.local.MoviesDao
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.domain.model.MovieDetails
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val localDataSource: MoviesDao,
    private val remoteDataSource: MovieApi
) : DetailsRepository{
    override fun getMovieDetails(imdbID: String): Single<MovieDetails> {
        return remoteDataSource.getMovieDetails(imdbID)
    }

    override fun getAllDetails(): Single<List<MovieDetails>> {
        return localDataSource.getAllDetails()
    }

    override fun insertDetails(movieDetails: MovieDetails): Single<Long> {
        return localDataSource.insertDetails(movieDetails)
    }

    override fun getDetails(detailsImdbID: String): Single<MovieDetails> {
        return localDataSource.getDetails(detailsImdbID)
    }
}