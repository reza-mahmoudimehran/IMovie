package ir.reza_mahmoudi.imovie.data.repository.home

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.data.local.MoviesDao
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.domain.model.SearchResponse
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val localDataSource: MoviesDao,
    private val remoteDataSource: MovieApi
)  : HomeRepository {
    override fun getMovies(searchText: String): Single<SearchResponse> {
        return remoteDataSource.getMovies(searchText)
    }

    override fun getAllSearchCase(): Single<List<SearchResponse>> {
        return localDataSource.getAllSearchCase()
    }

    override fun insertSearchCase(movieDetails: SearchResponse): Single<Long> {
        return localDataSource.insertSearchCase(movieDetails)
    }

    override fun getSearchCase(searchText: String): Single<SearchResponse> {
        return localDataSource.getSearchCase(searchText)
    }
}