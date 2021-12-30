package com.wonjoong.data.repository

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.api.GithubApi
import com.wonjoong.data.model.GithubUserInfo
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {
    override suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo {
        return githubApi.getUserInfo(userId)
    }
}