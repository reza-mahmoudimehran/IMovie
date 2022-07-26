package ir.reza_mahmoudi.imovie.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ir.reza_mahmoudi.imovie.data.model.MovieItem
import ir.reza_mahmoudi.imovie.databinding.ActivityHomeBinding
import ir.reza_mahmoudi.imovie.ui.moviedetails.MovieDetailsActivity
import ir.reza_mahmoudi.imovie.utils.ConnectionLiveData
import ir.reza_mahmoudi.imovie.utils.isConnected

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var moviesAdapter:MoviesListAdapter
    private lateinit var connectionLiveData: ConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
        playLoadingAnimation()
        observeViewModel()
    }
    private fun initViewModel(){
        viewModel= ViewModelProvider(this)[HomeViewModel::class.java]

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) {
            viewModel.isNetworkAvailable.value = it
        }
        viewModel.isNetworkAvailable.value = isConnected()

        viewModel.searchMovies("Batman")
    }
    private fun initViews(){
        moviesAdapter=MoviesListAdapter(arrayListOf()){
            startActivity(
                Intent(this@HomeActivity,MovieDetailsActivity::class.java)
                    .putExtra("imdbID",it)
            )
        }
        binding.moviesList.apply {
            layoutManager= LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
            adapter=moviesAdapter
        }

        binding.searchButton.setOnClickListener {
            viewModel.searchMovies(binding.searchText.text.toString())
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing=false
            viewModel.searchMovies(binding.searchText.text.toString())
        }
    }
    private fun playLoadingAnimation(){
        binding.loadingAnimation.setAnimation("loading_animation.json")
        binding.loadingAnimation.playAnimation()
    }
    private fun observeViewModel(){
        viewModel.movies.observe(this) { movies: List<MovieItem>? ->
            movies?.let {
                binding.moviesList.visibility = View.VISIBLE
                moviesAdapter.updateMovies(it)
            }
        }
        viewModel.moviesLoadError.observe(this) { isError: Boolean? ->
            isError?.let {
                binding.errorMessage.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        viewModel.loading.observe(this) { isLoading: Boolean? ->
            isLoading?.let {
                binding.loadingAnimation.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.errorMessage.visibility = View.GONE
                    binding.moviesList.visibility = View.GONE
                }
            }
        }
    }
}