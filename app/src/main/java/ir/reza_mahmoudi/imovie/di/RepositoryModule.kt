package ir.reza_mahmoudi.imovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.reza_mahmoudi.imovie.data.local.MoviesDao
import ir.reza_mahmoudi.imovie.data.remote.MovieApi
import ir.reza_mahmoudi.imovie.data.repository.details.DetailsRepository
import ir.reza_mahmoudi.imovie.data.repository.details.DetailsRepositoryImpl
import ir.reza_mahmoudi.imovie.data.repository.home.HomeRepository
import ir.reza_mahmoudi.imovie.data.repository.home.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDetailsRepositoryImpl(
        localDataSource: MoviesDao,
        remoteDataSource: MovieApi
    ) = DetailsRepositoryImpl(localDataSource,remoteDataSource)

    @Singleton
    @Provides
    fun provideDetailsRepository(repository: DetailsRepositoryImpl): DetailsRepository = repository

    @Singleton
    @Provides
    fun provideHomeRepositoryImpl(
        localDataSource: MoviesDao,
        remoteDataSource: MovieApi
    ) = HomeRepositoryImpl(localDataSource,remoteDataSource)

    @Singleton
    @Provides
    fun provideHomeRepository(repository: HomeRepositoryImpl): HomeRepository = repository
}