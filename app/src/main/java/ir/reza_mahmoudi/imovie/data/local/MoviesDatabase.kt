package ir.reza_mahmoudi.imovie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.reza_mahmoudi.imovie.model.MovieDetails
import ir.reza_mahmoudi.imovie.model.MovieItem
import ir.reza_mahmoudi.imovie.model.SearchResponse
import ir.reza_mahmoudi.imovie.utils.Constants.Companion.DATABASE_NAME

@Database(
    entities = [MovieItem::class, MovieDetails::class,SearchResponse::class],
    version = 1 ,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        // Volatile annotation means any change to this field
        // are immediately visible to other threads.
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            // here synchronised used for blocking the other thread
            // from accessing another while in a specific code execution.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}