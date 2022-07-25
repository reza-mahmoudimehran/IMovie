package ir.reza_mahmoudi.imovie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ir.reza_mahmoudi.imovie.R
import ir.reza_mahmoudi.imovie.data.model.MovieItem
import ir.reza_mahmoudi.imovie.data.model.SearchResponse
import ir.reza_mahmoudi.imovie.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private val moviesAdapter=MoviesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        playLoadingAnimation()
        observeViewModel()
    }
    private fun initViews(){
        viewModel= ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.searchMovies("Batman")

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