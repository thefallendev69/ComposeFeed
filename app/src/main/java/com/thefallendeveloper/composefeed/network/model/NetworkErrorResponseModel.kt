package com.thefallendeveloper.composefeed.network.model

data class NetworkErrorResponseModel(
    val errorMessage: String,
    val errorCode: Int,
    val exception: Exception?
)