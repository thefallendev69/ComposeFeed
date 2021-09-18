package com.thefallendeveloper.composefeed.network

import com.thefallendeveloper.composefeed.network.model.PostFeedModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface INetworkService {
    @GET
    suspend fun getPublicPosts(@Url url: String): Response<PostFeedModel>
}