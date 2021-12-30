package com.wonjoong.data.api

import com.google.gson.annotations.SerializedName
import com.wonjoong.data.model.GithubUserInfo
import retrofit2.http.Path

interface GithubApi {
    @SerializedName("users/{userId}")
    fun getUserInfo(
        @Path("userId") userId: String
    ): GithubUserInfo
}