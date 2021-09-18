package com.thefallendeveloper.composefeed.repositories

import com.thefallendeveloper.composefeed.usecases.model.PostFeedUIModel

interface IFeedRepository {

    suspend fun fetchFeedPosts(url: String): PostFeedUIModel
}