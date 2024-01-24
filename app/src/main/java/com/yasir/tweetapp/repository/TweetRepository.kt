package com.yasir.tweetapp.repository

import com.yasir.tweetapp.api.TweetsyApi
import com.yasir.tweetapp.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories:StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets:StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories(){
        val response = tweetsyApi.getCategories()
        if(response.isSuccessful && response.body() != null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String){
        val response = tweetsyApi.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() != null){
            _tweets.emit(response.body()!!)
        }
    }
}