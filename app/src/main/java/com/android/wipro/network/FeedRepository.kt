package com.android.wipro.network

import com.android.wipro.BuildConfig
import com.android.wipro.model.ResponseFeed
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

const val BASE_URL = "https://dl.dropboxusercontent.com"

interface FeedRepository {
    @GET
    fun FeedData(
        @Url url: String
    ): Observable<ResponseFeed>
}

fun createGithubService(): FeedRepository {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()
        .create(FeedRepository::class.java)
}
