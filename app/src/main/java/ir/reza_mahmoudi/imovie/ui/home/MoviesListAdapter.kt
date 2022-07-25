package ir.reza_mahmoudi.imovie.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.reza_mahmoudi.imovie.data.model.MovieItem
import ir.reza_mahmoudi.imovie.databinding.ItemMovieBinding
import ir.reza_mahmoudi.imovie.utils.getProgressDrawable
import ir.reza_mahmoudi.imovie.utils.loadImage

class MoviesListAdapter (private var movies: ArrayList<MovieItem>,
                         private val onMovieClicked: (imdbId: String) -> Unit) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies:List<MovieItem>){
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.movieItemMainLayout.setOnClickListener {
            movies[position].imdbID?.let { imdbID -> onMovieClicked(imdbID) }
        }
    }

    override fun getItemCount(): Int =movies.size

    class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        private val progressDrawable= getProgressDrawable(binding.root.context)
        val movieItemMainLayout =binding.movieItemMainLayout
        fun bind(movie: MovieItem){
            binding.title.text=movie.title
            binding.type.text=movie.type
            binding.year.text=movie.year
            binding.coverImage.loadImage(movie.poster,progressDrawable)
        }
    }
}