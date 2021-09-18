package com.thefallendeveloper.composefeed.usecases.model


data class PostFeedUIModel(
    val previousLink: String,
    val nextLink: String,
    val currentLink: String,
    val uiItems: ArrayList<PostFeedItemUIModel>
) : BaseUIModel()


data class PostFeedItemUIModel(
    val title: String,
    val description: String
)
