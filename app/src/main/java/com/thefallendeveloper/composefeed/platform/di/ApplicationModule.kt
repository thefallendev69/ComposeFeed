package com.thefallendeveloper.composefeed.platform.di

import com.thefallendeveloper.composefeed.platform.application.ComposeFeedApplication
import com.thefallendeveloper.composefeed.network.CONST_BASE_URL
import com.thefallendeveloper.composefeed.network.INetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    companion object {
        @Provides
        fun provideApplication(): ComposeFeedApplication {
            return ComposeFeedApplication.Instance
        }

        @Provides
        @Singleton
        fun provideNetworkService(): INetworkService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okhttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofitInstance = Retrofit.Builder()
                .baseUrl(CONST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
            return retrofitInstance.build()
                .create(INetworkService::class.java)
        }
    }
}