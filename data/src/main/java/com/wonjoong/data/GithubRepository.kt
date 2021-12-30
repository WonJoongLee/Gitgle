package com.wonjoong.data

import com.wonjoong.data.model.GithubUserInfo

interface GithubRepository {
    suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo
}