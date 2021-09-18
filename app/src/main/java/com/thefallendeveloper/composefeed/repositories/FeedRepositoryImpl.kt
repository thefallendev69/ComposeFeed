package com.thefallendeveloper.composefeed.repositories

import com.thefallendeveloper.composefeed.usecases.GetFeedsUseCase
import com.thefallendeveloper.composefeed.usecases.model.PostFeedUIModel
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val getFeedsUseCase: GetFeedsUseCase
) : IFeedRepository {
    override suspend fun fetchFeedPosts(url: String): PostFeedUIModel {
        return getFeedsUseCase.executeUseCase(
            GetFeedsUseCase.Parameters(
                url = url
            )
        )
    }
}