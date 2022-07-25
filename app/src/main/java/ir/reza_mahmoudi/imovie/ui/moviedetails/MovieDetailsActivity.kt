package ir.reza_mahmoudi.imovie.ui.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ir.reza_mahmoudi.imovie.databinding.ActivityMovieDetailsBinding
import ir.reza_mahmoudi.imovie.ui.home.HomeViewModel

class MovieDetailsActivity : AppCompatActivity() {
    lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }
    private fun initViews(){
        viewModel= ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        viewModel.getDetails("tt0372784")

    }
}