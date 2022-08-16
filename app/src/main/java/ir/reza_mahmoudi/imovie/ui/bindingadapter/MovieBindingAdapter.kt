package ir.reza_mahmoudi.imovie.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ir.reza_mahmoudi.imovie.utils.getProgressDrawable
import ir.reza_mahmoudi.imovie.utils.loadImage

class MovieBindingAdapter {
    companion object {
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageUrl: String) {
            val progressDrawable = getProgressDrawable(imageView.context)
            imageView.loadImage(imageUrl, progressDrawable)
        }
    }
}