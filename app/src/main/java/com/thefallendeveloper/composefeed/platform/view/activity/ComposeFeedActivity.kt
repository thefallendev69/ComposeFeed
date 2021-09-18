package com.thefallendeveloper.composefeed.platform.view.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.thefallendeveloper.composefeed.platform.view.composables.ErrorButton
import com.thefallendeveloper.composefeed.platform.view.composables.FeedItem
import com.thefallendeveloper.composefeed.platform.view.composables.FeedItemPlaceHolder
import com.thefallendeveloper.composefeed.platform.view.composables.PaginationLoader
import com.thefallendeveloper.composefeed.platform.viewmodel.ComposeFeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeFeedActivity : AppCompatActivity() {

    val composeFeedViewModel by viewModels<ComposeFeedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val feedItems = composeFeedViewModel.feedListPager.flow.collectAsLazyPagingItems()
                LazyColumn {
                    items(feedItems) { feed ->
                        if (feed != null) {
                            FeedItem(item = feed)
                        } else {
                            FeedItemPlaceHolder()
                        }
                    }
                    feedItems.apply {
                        when {
                            loadState.prepend is LoadState.Loading ||
                                    loadState.refresh is LoadState.Loading ||
                                    loadState.append is LoadState.Loading -> {
                                item {
                                    PaginationLoader(itemList = feedItems)
                                }
                            }
                            loadState.prepend is LoadState.Error -> {
                                item {
                                    ErrorButton(
                                        paginationList = feedItems,
                                        loadState = loadState.prepend as LoadState.Error,
                                        onRetryClick = {
                                            retry()
                                        })
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                item {
                                    ErrorButton(
                                        paginationList = feedItems,
                                        loadState = loadState.refresh as LoadState.Error,
                                        onRetryClick = {
                                            retry()
                                        }
                                    )
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                item {
                                    ErrorButton(
                                        paginationList = feedItems,
                                        loadState = loadState.append as LoadState.Error,
                                        onRetryClick = {
                                            retry()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}