package ir.reza_mahmoudi.imovie.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.reza_mahmoudi.imovie.data.model.MovieItem


class Converters {
    @TypeConverter
    fun restoreList(listOfString: String?): List<MovieItem?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<MovieItem?>?>() {}.type)
    }
    @TypeConverter
    fun saveList(listOfString: List<MovieItem?>?): String? {
        return Gson().toJson(listOfString)
    }
}