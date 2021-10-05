package com.kurshin.tvbank.di_module

import com.google.gson.JsonParser
import com.kurshin.tvbank.BuildConfig
import com.kurshin.tvbank.network.api.PrivatApi
import com.kurshin.tvbank.network.interceptor.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = "https://api.privatbank.ua"
        val loginLevel =  HttpLoggingInterceptor.Level.BASIC
    }

    @Singleton
    @Provides
    fun provideJsonParser(): JsonParser {
        return JsonParser()
    }

    @Provides
    fun commonOkHttpClientBuilder(
        errorInterceptor: ErrorInterceptor
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(errorInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

    @Provides
    fun retrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())

    @Provides
    fun getPrivatApi(
        retrofit: Retrofit.Builder,
        commonOkHttpClientBuilder: OkHttpClient.Builder
    ): PrivatApi {
        if (BuildConfig.DEBUG) {
            commonOkHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = loginLevel
            })
        }
        return retrofit.client(commonOkHttpClientBuilder.build())
            .baseUrl(BASE_URL)
            .build()
            .create(PrivatApi::class.java)
    }
}