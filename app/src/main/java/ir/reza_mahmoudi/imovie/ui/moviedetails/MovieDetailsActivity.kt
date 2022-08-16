package ir.reza_mahmoudi.imovie.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ir.reza_mahmoudi.imovie.domain.model.MovieDetails
import ir.reza_mahmoudi.imovie.databinding.ActivityMovieDetailsBinding
import ir.reza_mahmoudi.imovie.databinding.IncludeMovieDetailsBinding
import ir.reza_mahmoudi.imovie.utils.ConnectionLiveData
import ir.reza_mahmoudi.imovie.utils.getProgressDrawable
import ir.reza_mahmoudi.imovie.utils.isConnected
import ir.reza_mahmoudi.imovie.utils.loadImage

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var detailsBinding: IncludeMovieDetailsBinding
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        playLoadingAnimation()
        observeViewModel()
    }
    private fun initViews(){
        detailsBinding = binding.details

        viewModel= ViewModelProvider(this)[MovieDetailsViewModel::class.java]

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) {
            viewModel.isNetworkAvailable.value = it
        }
        viewModel.isNetworkAvailable.value = isConnected()

        val imdbID =intent.getStringExtra("imdbID")
        imdbID?.let { viewModel.getDetails(it) }
    }
    private fun playLoadingAnimation(){
        binding.loadingAnimation.setAnimation("loading_animation.json")
        binding.loadingAnimation.playAnimation()
    }
    private fun observeViewModel(){
        viewModel.movieDetails.observe(this) { movieDetails: MovieDetails? ->
            movieDetails?.let {
                detailsBinding.details.visibility = View.VISIBLE
                detailsBinding.movieDetails=it
            }
        }
        viewModel.moviesLoadError.observe(this) { isError: Boolean? ->
            isError?.let {
                binding.errorMessage.visibility = if (it) View.VISIBLE else View.GONE
                detailsBinding.details.visibility = if (it) View.GONE else View.VISIBLE
            }
        }
        viewModel.loading.observe(this) { isLoading: Boolean? ->
            isLoading?.let {
                binding.loadingAnimation.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.errorMessage.visibility = View.GONE
                    detailsBinding.details.visibility = View.GONE
                }
            }
        }
    }
}