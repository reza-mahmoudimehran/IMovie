package ir.reza_mahmoudi.imovie.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ir.reza_mahmoudi.imovie.data.local.MoviesDatabase
import ir.reza_mahmoudi.imovie.model.MovieItem
import ir.reza_mahmoudi.imovie.model.SearchResponse
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.di.DaggerApiComponent
import ir.reza_mahmoudi.imovie.utils.showLog
import javax.inject.Inject

class HomeViewModel (application: Application
) : AndroidViewModel(application) {
    @Inject
    lateinit var movieApi:MovieApi
    init{
        DaggerApiComponent.create().inject(this)
    }
    private val disposable = CompositeDisposable()
    private val movieDao = MoviesDatabase.getDatabase(application).getMoviesDao()

    val movies = MutableLiveData<List<MovieItem>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val isNetworkAvailable = MutableLiveData<Boolean>()

    fun searchMovies(searchText:String){
        val text=searchText.lowercase()
        if (isNetworkAvailable.value==true){
            search(text)
        }else {
            getLocalList(text)
            showLog("Network: ",isNetworkAvailable.value.toString())
        }
    }

    private fun search(searchText:String){
        loading.value = true
        disposable.add(
            movieApi.getMovies(searchText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<SearchResponse>(), Disposable {
                    override fun onSuccess(searchCase: SearchResponse) {
                        showLog("searchMovies: ", searchText)
                        searchCase.searchText=searchText
                        movies.value = searchCase.searchList
                        insertSearchCase(searchCase)
                        setCompleteSuccessfully()
                    }
                    override fun onError(e: Throwable) {
                        showLog("searchMovies: ",e.toString())
                        setCompleteWithError()
                    }
                })
        )
    }
    private fun getLocalList(searchText:String){
        disposable.add(
            movieDao.getSearchCase(searchText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<SearchResponse>(), Disposable {
                    override fun onSuccess(searchCase: SearchResponse) {
                        showLog("getLocalList: ",searchCase.searchText)
                        movies.value =searchCase.searchList
                        setCompleteSuccessfully()
                    }
                    override fun onError(e: Throwable) {
                        showLog("getLocalList: ",e.toString())
                        movies.value=listOf()
                        setCompleteWithError()
                    }
                })
        )
    }

    private fun insertSearchCase(searchCase: SearchResponse){
        disposable.add(
            movieDao.insertSearchCase(searchCase).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Long>(), Disposable {
                    override fun onSuccess(id: Long) {
                        showLog("insertSearchCase: ",id.toString())
                    }
                    override fun onError(e: Throwable) {
                        showLog("insertSearchCase: ",e.toString())
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