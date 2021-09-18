package com.thefallendeveloper.composefeed.platform.di

import com.thefallendeveloper.composefeed.repositories.FeedRepositoryImpl
import com.thefallendeveloper.composefeed.repositories.IFeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    companion object {
        @Provides
        fun provideFeedRepository(repo: FeedRepositoryImpl): IFeedRepository {
            return repo
        }
    }
}