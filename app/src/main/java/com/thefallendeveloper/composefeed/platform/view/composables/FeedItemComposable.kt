package com.thefallendeveloper.composefeed.platform.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thefallendeveloper.composefeed.usecases.model.PostFeedItemUIModel

@Composable
fun FeedItem(item: PostFeedItemUIModel) {
    Card(
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 16.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.title,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.description,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )

        }
    }
}

@Preview
@Composable
fun FeedItemPlaceHolder() {

    Card(
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 16.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
        }
    }

}

@Preview
@Composable
fun FeedItemPreview() {
    val feedItem = PostFeedItemUIModel(
        title = "This is a sample Title",
        description = "This is a small samplsdlkfjsdlkfjsdlkfjslkgjhsdfglkhfdgkldfhgjkdfslhgdsfjkhgsdfjklghfghjjdflkgjhdsfkgljhdfgkljdglksjfglkdfjglkdfjgdlskjgdslkjgdslkfjgdslkgjdskljgdsfklgjdslkgjdsklgjlkgjdsflkgjdsfklgjdflkgje description of the given title"
    )
    FeedItem(item = feedItem)
}