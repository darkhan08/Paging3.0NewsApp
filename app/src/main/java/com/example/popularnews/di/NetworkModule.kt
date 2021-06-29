package com.example.popularnews.di

import androidx.multidex.BuildConfig
import com.example.popularnews.data.api.NewsApiService
import com.example.popularnews.utils.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideInterceptor() }
    single { provideNewsOkHttpClient(get()) }
    single { provideNewsApiService(get()) }
}

fun provideNewsOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()
}

fun provideInterceptor(): Interceptor {
    return Interceptor { chain ->
        val uri = chain.request()
            .url
            .newBuilder()
//            .addQueryParameter("from", FROM_DATE)
//            .addQueryParameter("to", TO_DATE)
            .addQueryParameter("language", LANGUAGE)
            .addQueryParameter("sortBy", SORTED_BY)
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(uri)
            .build()
        return@Interceptor chain.proceed(request)
    }
}

fun provideNewsApiService(client: OkHttpClient): NewsApiService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URI)
        .build()
    return retrofit.create(NewsApiService::class.java)
}