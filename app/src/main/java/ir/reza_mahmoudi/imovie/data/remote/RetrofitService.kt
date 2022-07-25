package ir.reza_mahmoudi.imovie.data.remote

import io.reactivex.Single
import ir.reza_mahmoudi.imovie.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    fun<T> buildService(service : Class<T>): T {
        return retrofit.create(service)
    }
}