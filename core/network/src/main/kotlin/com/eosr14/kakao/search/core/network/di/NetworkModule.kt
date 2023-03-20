package com.eosr14.kakao.search.core.network.di

import com.eosr14.kakao.search.core.network.adapter.MoshiDateAdapter
import com.eosr14.kakao.search.core.network.service.KakaoService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    private const val URL_HOST = "https://dapi.kakao.com"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).apply {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(URL_HOST)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()
    }

    @Provides
    @Singleton
    fun provideKakaoService(retrofit: Retrofit): KakaoService =
        retrofit.create(KakaoService::class.java)

    private fun getMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, MoshiDateAdapter().nullSafe())
        .build()
}
