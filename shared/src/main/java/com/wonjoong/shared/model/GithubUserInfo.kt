package com.wonjoong.shared.model

import com.google.gson.annotations.SerializedName

data class GithubUserInfo(
    val name: String?,
    @SerializedName("avatar_url")
    val profileUrl: String?,
    val followers: String?,
    val following: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    var isFavorite: Boolean = false
)
