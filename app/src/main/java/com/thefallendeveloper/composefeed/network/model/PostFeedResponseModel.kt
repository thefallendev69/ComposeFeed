package com.thefallendeveloper.composefeed.network.model

import com.google.gson.annotations.SerializedName

data class PostFeedModel(
    @SerializedName("meta")
    val meta: PostFeedMetaModel?,
    @SerializedName("data")
    val data: ArrayList<PostFeedItemModel>?
): BaseNetworkResponseModel()

data class PostFeedItemModel(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("user_id")
    val userId: Long?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?
): BaseNetworkResponseModel()

data class PostFeedMetaModel(
    @SerializedName("pagination")
    val pagination: PostFeedPaginationModel?
): BaseNetworkResponseModel()

data class PostFeedPaginationModel(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("links")
    val links: PostFeedLinksModel?
): BaseNetworkResponseModel()

data class PostFeedLinksModel(
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("current")
    val current: String?,
    @SerializedName("next")
    val next: String?,
): BaseNetworkResponseModel()