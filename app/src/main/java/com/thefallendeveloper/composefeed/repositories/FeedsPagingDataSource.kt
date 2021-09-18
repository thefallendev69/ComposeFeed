package com.thefallendeveloper.composefeed.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thefallendeveloper.composefeed.network.CONST_API_PUBLIC_POSTS_URL
import com.thefallendeveloper.composefeed.usecases.model.PostFeedItemUIModel
import javax.inject.Inject

class FeedsPagingDataSource @Inject constructor(
    val repo: IFeedRepository,
) : PagingSource<String, PostFeedItemUIModel>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PostFeedItemUIModel> {
        val nextPage = params.key ?: CONST_API_PUBLIC_POSTS_URL
        val response = repo.fetchFeedPosts(url = nextPage)
        return if (response.errorUIModel == null) {
            LoadResult.Page(
                data = response.uiItems,
                prevKey = response.previousLink,
                nextKey = if (response.nextLink.isBlank()) null else response.nextLink
            )
        } else {
            LoadResult.Error(response.errorUIModel!!)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PostFeedItemUIModel>): String? {
        return null
    }
}