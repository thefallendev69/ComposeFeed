package com.thefallendeveloper.composefeed.platform.application

import android.app.Application
import android.util.Log
import com.thefallendeveloper.composefeed.network.INetworkService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Singleton

private lateinit var _instance: ComposeFeedApplication

@HiltAndroidApp
class ComposeFeedApplication : Application() {

    companion object {
        const val TAG = "ComposeFeedApplication"
        val Instance: ComposeFeedApplication
            get() = _instance
    }

    @Inject
    @Singleton
    lateinit var networkService: INetworkService

    override fun onCreate() {
        super.onCreate()
        _instance = this
        Log.d(TAG, networkService.toString())
    }

}