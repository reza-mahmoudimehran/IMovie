package ir.reza_mahmoudi.imovie.di

import dagger.Component
import ir.reza_mahmoudi.imovie.ui.home.HomeViewModel
import ir.reza_mahmoudi.imovie.ui.moviedetails.MovieDetailsViewModel

@Component(modules=[ApiModule::class])
interface ApiComponent {
    fun inject(viewModel: HomeViewModel)
    fun inject(viewModel: MovieDetailsViewModel)
}