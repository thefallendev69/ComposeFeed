package com.thefallendeveloper.composefeed.platform.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.thefallendeveloper.composefeed.network.CONST_API_PUBLIC_POSTS_URL
import com.thefallendeveloper.composefeed.platform.CONST_FEED_BATCH_SIZE
import com.thefallendeveloper.composefeed.platform.CONST_FEED_PAGE_SIZE
import com.thefallendeveloper.composefeed.platform.application.ComposeFeedApplication
import com.thefallendeveloper.composefeed.repositories.FeedsPagingDataSource
import com.thefallendeveloper.composefeed.repositories.IFeedRepository
import com.thefallendeveloper.composefeed.usecases.model.ErrorUIModel
import com.thefallendeveloper.composefeed.usecases.model.PostFeedItemUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ComposeFeedViewModel @Inject constructor(
    application: ComposeFeedApplication,
    val repo: IFeedRepository,
    val pagingSource: FeedsPagingDataSource
) : AndroidViewModel(application) {

    private val _feedLstPager = Pager(
        PagingConfig(
            pageSize = CONST_FEED_PAGE_SIZE,
            maxSize = CONST_FEED_BATCH_SIZE,
        )
    ) {
        pagingSource
    }
    val feedListPager: Pager<String, PostFeedItemUIModel>
        get() = _feedLstPager

}