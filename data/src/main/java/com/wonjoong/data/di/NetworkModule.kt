package com.wonjoong.data.di

import com.wonjoong.data.api.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {
    private val GITHUB_BASE_URL = "https://api.github.com/"
    @Provides
    @Singleton
    fun provideGithubApi() =
        Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .build()
            .create(GithubApi::class.java)
}