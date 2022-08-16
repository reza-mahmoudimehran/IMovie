package ir.reza_mahmoudi.imovie.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ir.reza_mahmoudi.imovie.domain.model.MovieDetails
import ir.reza_mahmoudi.imovie.data.repository.details.DetailsRepository
import ir.reza_mahmoudi.imovie.utils.showLog
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {
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
            detailsRepository.getMovieDetails(imdbID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieDetails>(), Disposable {
                    override fun onSuccess(details: MovieDetails) {
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
            detailsRepository.getDetails(imdbID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieDetails>(), Disposable {
                    override fun onSuccess(details: MovieDetails) {
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
            detailsRepository.insertDetails(movieDetails).subscribeOn(Schedulers.newThread())
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