package ir.reza_mahmoudi.imovie.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ir.reza_mahmoudi.imovie.data.model.MovieDetails
import ir.reza_mahmoudi.imovie.databinding.ActivityMovieDetailsBinding
import ir.reza_mahmoudi.imovie.databinding.IncludeMovieDetailsBinding
import ir.reza_mahmoudi.imovie.utils.getProgressDrawable
import ir.reza_mahmoudi.imovie.utils.loadImage

class MovieDetailsActivity : AppCompatActivity() {
    lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var detailsBinding: IncludeMovieDetailsBinding

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
                updateDetails(it)
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
                    detailsBinding.details.visibility = View.GONE
                }
            }
        }
    }
    private fun updateDetails(movie:MovieDetails){
        detailsBinding.year.text=movie.year
        detailsBinding.director.text=movie.director
        detailsBinding.title.text=movie.title
        detailsBinding.genre.text=movie.genre
        detailsBinding.time.text=movie.time
        detailsBinding.imdbRating.text=movie.imdbRating
        detailsBinding.metaRating.text=movie.metaScore
        detailsBinding.summary.text=movie.summary
        detailsBinding.poster.loadImage(movie.poster,getProgressDrawable(binding.root.context))
    }
}