package com.wonjoong.data.model

import com.google.gson.annotations.SerializedName

data class GithubUserInfo(
    val name: String?,
    @SerializedName("avatar_url")
    val profileUrl: String?,
    val followers: String?,
    val following: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    // If there is no target user(or repository),
    // this message will be "Not Found"
    val message: String?
)
