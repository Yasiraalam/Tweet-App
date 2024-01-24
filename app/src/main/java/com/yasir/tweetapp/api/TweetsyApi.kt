package com.yasir.tweetapp.api

import com.yasir.tweetapp.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT

interface TweetsyApi {

    @GET("/v3/b/65a157601f5677401f1bcc36?meta=false")
    suspend fun getTweets(
        @Header("X-JSON-Path")category: String
    ):Response<List<TweetListItem>>

    @GET("/v3/b/65a157601f5677401f1bcc36?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories():Response<List<String>>
}