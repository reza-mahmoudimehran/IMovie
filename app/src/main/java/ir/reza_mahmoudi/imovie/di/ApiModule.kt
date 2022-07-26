package ir.reza_mahmoudi.imovie.di

import dagger.Module
import dagger.Provides
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.data.remote.RetrofitService

@Module
class ApiModule {
    @Provides
    fun provideMovieApi()=  RetrofitService.buildService(MovieApi::class.java)
}