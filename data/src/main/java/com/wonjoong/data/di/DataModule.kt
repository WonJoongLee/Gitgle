package com.wonjoong.data.di

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindsGithubRepository(
        repository: GithubRepositoryImpl
    ): GithubRepository
}