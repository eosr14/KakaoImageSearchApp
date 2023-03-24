package com.eosr14.kakao.search.core.network.service

import com.eosr14.kakao.search.core.model.ImageResponse
import com.eosr14.kakao.search.core.model.VideoResponse
import com.eosr14.kakao.search.core.network.utils.DEFAULT_PAGE
import com.eosr14.kakao.search.core.network.utils.NETWORK_DEFAULT_SIZE
import retrofit2.http.*

interface KakaoService {
    @Headers("Authorization: $API_AUTHORIZATION_PREFIX $API_ACCESS_KEY")
    @GET("/v2/search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("sort") sort: String = DEFAULT_SORT,
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("size") size: Int = NETWORK_DEFAULT_SIZE
    ): ImageResponse

    @Headers("Authorization: $API_AUTHORIZATION_PREFIX $API_ACCESS_KEY")
    @GET("/v2/search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("sort") sort: String = DEFAULT_SORT,
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("size") size: Int = NETWORK_DEFAULT_SIZE
    ): VideoResponse
}

private const val API_AUTHORIZATION_PREFIX = "KakaoAK"
private const val API_ACCESS_KEY = "6ff95cfba8fa9f36d514c6eab63b748a"
private const val DEFAULT_SORT = "recency"
