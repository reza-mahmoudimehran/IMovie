package ir.reza_mahmoudi.imovie.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ir.reza_mahmoudi.imovie.data.model.MovieDetails
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.data.remote.RetrofitService
import ir.reza_mahmoudi.imovie.utils.showLog

class MovieDetailsViewModel : ViewModel(){
    private val movieApi= RetrofitService.buildService(MovieApi::class.java)
    private val disposable = CompositeDisposable()
    val movieDetails = MutableLiveData<MovieDetails>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getDetails(imdbID:String){
        details(imdbID)
    }
    private fun details(imdbID:String){
        loading.value = true
        disposable.add(
            movieApi.getMovieDetails(imdbID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieDetails>(), Disposable {
                    override fun onSuccess(value: MovieDetails?) {
                        showLog("searchMovies: ", value?.toString() ?: "null ")
                        movieDetails.value = value
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