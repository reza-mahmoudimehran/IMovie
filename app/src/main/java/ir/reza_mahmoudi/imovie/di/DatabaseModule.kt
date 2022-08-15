package ir.reza_mahmoudi.imovie.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.reza_mahmoudi.imovie.data.local.MoviesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = MoviesDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideDao(database: MoviesDatabase) = database.getMoviesDao()

}