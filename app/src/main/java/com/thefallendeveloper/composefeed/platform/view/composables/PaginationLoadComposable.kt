package com.thefallendeveloper.composefeed.platform.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.thefallendeveloper.composefeed.usecases.model.BaseUIModel

@Composable
fun <T: Any>PaginationLoader(itemList: LazyPagingItems<T>) {
    if(itemList.itemCount > 0) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp, 32.dp)
            )
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp, 64.dp)
            )
        }
    }
}