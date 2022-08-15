package ir.reza_mahmoudi.imovie.data.repository.home

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.model.SearchResponse

class HomeRepositoryImpl : HomeRepository{
    override fun getMovies(searchText: String): Single<SearchResponse> {
        TODO("Not yet implemented")
    }

    override fun getAllSearchCase(): Single<List<SearchResponse>> {
        TODO("Not yet implemented")
    }

    override fun insertSearchCase(movieDetails: SearchResponse): Single<Long> {
        TODO("Not yet implemented")
    }

    override fun getSearchCase(searchText: String): Single<SearchResponse> {
        TODO("Not yet implemented")
    }
}