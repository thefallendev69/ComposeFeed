package com.thefallendeveloper.composefeed.usecases.model

class ErrorUIModel(
    val errorMessage: String,
    val exception: Exception?,
    val errorCode: Int?
): Throwable()