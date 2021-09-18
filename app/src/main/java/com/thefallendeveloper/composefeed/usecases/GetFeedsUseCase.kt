package com.thefallendeveloper.composefeed.usecases

import android.util.Log
import com.thefallendeveloper.composefeed.network.CONST_CLIENT_ERROR_CODE
import com.thefallendeveloper.composefeed.network.INetworkService
import com.thefallendeveloper.composefeed.network.model.NetworkErrorResponseModel
import com.thefallendeveloper.composefeed.network.model.PostFeedModel
import com.thefallendeveloper.composefeed.usecases.model.PostFeedItemUIModel
import com.thefallendeveloper.composefeed.usecases.model.PostFeedUIModel
import java.lang.Exception
import java.net.URI
import java.net.URL
import javax.inject.Inject

private const val CONST_QUERY_PARAM_PAGE = "page"

class GetFeedsUseCase @Inject constructor(
    dataSource: INetworkService,
) : BaseUseCase<GetFeedsUseCase.Parameters, PostFeedModel, PostFeedUIModel>(dataSource) {

    companion object {
        const val TAG = "GetFeedsUseCase"
    }

    data class Parameters(
        val url: String
    ) : BaseUseCaseParams()

    override suspend fun executeApiRequest(params: Parameters?): PostFeedModel {
        return try {
            val url = params?.url ?: ""
            val responseModel = dataSource.getPublicPosts(url)
            if (responseModel.isSuccessful && responseModel.body() != null) {
                responseModel.body()!!
            } else {
                PostFeedModel(
                    meta = null,
                    data = null,
                ).apply {
                    networkErrorResponseModel = NetworkErrorResponseModel(
                        errorMessage = responseModel.message(),
                        errorCode = responseModel.code(),
                        exception = null,
                    )
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            e.printStackTrace()
            return PostFeedModel(
                meta = null,
                data = null,
            ).apply {
                networkErrorResponseModel = NetworkErrorResponseModel(
                    errorMessage = e.localizedMessage ?: "",
                    errorCode = CONST_CLIENT_ERROR_CODE,
                    exception = e
                )
            }
        }
    }

    override fun transform(input: PostFeedModel): PostFeedUIModel {
        val itemList = if (input.data.isNullOrEmpty()) ArrayList()
        else input.data.map {
            PostFeedItemUIModel(
                title = it.title ?: "",
                description = it.body ?: ""
            )
        }.toMutableList() as ArrayList<PostFeedItemUIModel>
        return PostFeedUIModel(
            previousLink = input.meta?.pagination?.links?.previous ?: "",
            nextLink = getCurrentPageNextLink(
                currentUrl = input.meta?.pagination?.links?.current,
                maxPage = input.meta?.pagination?.pages,
                nextUrl = input.meta?.pagination?.links?.next,
            ),
            currentLink = input.meta?.pagination?.links?.current ?: "",
            uiItems = itemList
        )
    }

    private fun getCurrentPageNextLink(
        currentUrl: String?,
        maxPage: Int?,
        nextUrl: String?
    ): String {
        try {
            val parsedUrl = URL(currentUrl)
            val query = parsedUrl.query?.split("&")
            val hashMap = HashMap<String, String>()
            if (query != null) {
                query.map {
                    val items = it.split("=")
                    if (items.size == 2) {
                        hashMap.put(items[0], items[1])
                    } else {
                        hashMap.put(items[0], "")
                    }
                }
                val currentPge = hashMap[CONST_QUERY_PARAM_PAGE]?.toInt()
                if (currentPge == maxPage) return ""
                else return nextUrl ?: ""
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            e.printStackTrace()
        }
        return ""
    }


}