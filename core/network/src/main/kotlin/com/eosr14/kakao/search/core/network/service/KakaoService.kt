package com.eosr14.kakao.search.core.network.service

import retrofit2.http.*
import com.eosr14.kakao.search.core.model.ImageResponse
import com.eosr14.kakao.search.core.model.VideoResponse

interface KakaoService {
    @Headers("Authorization: ${API_AUTHORIZATION_PREFIX} ${API_ACCESS_KEY}")
    @GET("/v2/search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("sort") sort: String = Sort.ACCURACY.name,
        @Query("page") page: Long = DEFAULT_PAGE,
        @Query("size") size: Int = NETWORK_DEFAULT_SIZE
    ): ImageResponse

    @Headers("Authorization: ${API_AUTHORIZATION_PREFIX} ${API_ACCESS_KEY}")
    @GET("/v2/search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("sort") sort: String = Sort.ACCURACY.name,
        @Query("page") page: Long = DEFAULT_PAGE,
        @Query("size") size: Int = NETWORK_DEFAULT_SIZE
    ): VideoResponse
}

private const val API_AUTHORIZATION_PREFIX = "KakaoAK"
private const val API_ACCESS_KEY = "6ff95cfba8fa9f36d514c6eab63b748a"