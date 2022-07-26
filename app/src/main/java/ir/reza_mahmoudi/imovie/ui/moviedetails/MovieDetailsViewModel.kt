package ir.reza_mahmoudi.imovie.ui.moviedetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ir.reza_mahmoudi.imovie.data.local.MoviesDatabase
import ir.reza_mahmoudi.imovie.data.model.MovieDetails
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.data.remote.RetrofitService
import ir.reza_mahmoudi.imovie.utils.showLog

class MovieDetailsViewModel(application: Application
) : AndroidViewModel(application) {
    private val movieDao = MoviesDatabase.getDatabase(application).getMoviesDao()
    private val movieApi= RetrofitService.buildService(MovieApi::class.java)
    private val disposable = CompositeDisposable()
    val movieDetails = MutableLiveData<MovieDetails>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val isNetworkAvailable = MutableLiveData<Boolean>()

    fun getDetails(imdbID:String){
        if (isNetworkAvailable.value==true){
            details(imdbID)
        }else {
            getLocalDetails(imdbID)
            showLog("Network: ",isNetworkAvailable.value.toString())
        }
    }
    private fun details(imdbID:String){
        loading.value = true
        disposable.add(
            movieApi.getMovieDetails(imdbID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieDetails>(), Disposable {
                    override fun onSuccess(details: MovieDetails) {
                        showLog("searchMovies: ", details.imdbID)
                        movieDetails.value = details
                        insertDetails(details)
                        setCompleteSuccessfully()
                    }
                    override fun onError(e: Throwable) {
                        showLog("searchMovies: ",e.toString())
                        setCompleteWithError()
                    }
                })
        )
    }
    private fun getLocalDetails(imdbID:String){
        disposable.add(
            movieDao.getDetails(imdbID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieDetails>(), Disposable {
                    override fun onSuccess(details: MovieDetails) {
                        showLog("getLocalDetails: ",details.imdbID)
                        movieDetails.value =details
                        setCompleteSuccessfully()
                    }
                    override fun onError(e: Throwable) {
                        showLog("getLocalDetails: ",e.toString())
                        setCompleteWithError()
                    }
                })
        )
    }
    private fun insertDetails(movieDetails: MovieDetails){
        disposable.add(
            movieDao.insertDetails(movieDetails).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Long>(), Disposable {
                    override fun onSuccess(id: Long) {
                        showLog("insertDetails: ",id.toString())
                    }
                    override fun onError(e: Throwable) {
                        showLog("insertDetails: ",e.toString())
                    }
                })
        )
    }

    private fun setCompleteWithError(){
        moviesLoadError.value = true
        loading.value = false
    }
    private fun setCompleteSuccessfully(){
        moviesLoadError.value = false
        loading.value = false
    }
}