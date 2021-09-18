package com.thefallendeveloper.composefeed.usecases

import com.thefallendeveloper.composefeed.network.INetworkService
import com.thefallendeveloper.composefeed.network.model.BaseNetworkResponseModel
import com.thefallendeveloper.composefeed.network.model.NetworkErrorResponseModel
import com.thefallendeveloper.composefeed.usecases.model.BaseUIModel
import com.thefallendeveloper.composefeed.usecases.model.ErrorUIModel

abstract class BaseUseCase<P : BaseUseCaseParams, R : BaseNetworkResponseModel, T : BaseUIModel>(
    val dataSource: INetworkService,
) {

    abstract protected suspend fun executeApiRequest(params: P? = null): R

    abstract protected fun transform(input: R): T

    private fun transformErrorModel(networkErrorResponseModel: NetworkErrorResponseModel?): ErrorUIModel? {
        if (networkErrorResponseModel != null) {
            return ErrorUIModel(
                errorMessage = networkErrorResponseModel.errorMessage ?: "",
                exception = networkErrorResponseModel.exception,
                errorCode = networkErrorResponseModel.errorCode
            )
        }
        return null
    }

    suspend fun executeUseCase(params: P?): T {
        val responseModel = executeApiRequest(params)
        val transformedErrorModel = transformErrorModel(responseModel.networkErrorResponseModel)
        val transformedModel = transform(responseModel)
        transformedModel.errorUIModel = transformedErrorModel
        return transformedModel
    }
}