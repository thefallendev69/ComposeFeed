package com.thefallendeveloper.composefeed.platform.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.thefallendeveloper.composefeed.R
import com.thefallendeveloper.composefeed.usecases.model.ErrorUIModel
import java.net.UnknownHostException

@Composable
fun <T : Any> ErrorButton(
    paginationList: LazyPagingItems<T>,
    loadState: LoadState.Error,
    onRetryClick: () -> Unit
) {
    val error = loadState.error
    if (error is ErrorUIModel) {
        val cardModifier = when (paginationList.itemCount) {
            0 -> Modifier.fillMaxSize()
            else -> Modifier.fillMaxWidth()

        }
        val columnModifier = when (paginationList.itemCount) {
            0 -> Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
            else -> Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        }
        val verticalArrangement = when (paginationList.itemCount) {
            0 -> Arrangement.Center
            else -> Arrangement.Top
        }
        Card(
            modifier = cardModifier,
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = columnModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = verticalArrangement
            ) {
                val message = when {
                    error.exception is UnknownHostException -> stringResource(id = R.string.app_no_network_error)
                    error.errorMessage.isNotBlank() && error.exception == null -> error.errorMessage
                    else -> stringResource(id = R.string.app_generic_error)
                }
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.size(16.dp))

                Button(
                    onClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red
                    ),
                    shape = RoundedCornerShape(16.dp),

                    ) {
                    Text(
                        text = stringResource(R.string.app_retry),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 16.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}