package ir.reza_mahmoudi.imovie.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ir.reza_mahmoudi.imovie.data.model.MovieItem
import ir.reza_mahmoudi.imovie.data.model.SearchResponse
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.data.remote.RetrofitService
import ir.reza_mahmoudi.imovie.utils.showLog

class HomeViewModel : ViewModel() {

    private val movieApi= RetrofitService.buildService(MovieApi::class.java)
    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<MovieItem>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun searchMovies(searchText:String){
        search(searchText)
    }

    private fun search(searchText:String){
        loading.value = true
        disposable.add(
            movieApi.getMovies(searchText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<SearchResponse>(), Disposable {
                    override fun onSuccess(value: SearchResponse?) {
                        //showLog("searchMovies: ", value?.search?.joinToString() ?: "null ")
                        movies.value = value?.search
                        moviesLoadError.value = false
                        loading.value = false
                    }
                    override fun onError(e: Throwable?) {
                        showLog("searchMovies: ",e.toString())
                        moviesLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }
}