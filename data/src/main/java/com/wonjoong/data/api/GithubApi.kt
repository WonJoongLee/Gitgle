package com.wonjoong.data.api

import com.wonjoong.data.model.GithubUserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: String
    ): GithubUserInfo
}