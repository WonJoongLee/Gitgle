package com.wonjoong.domain.usecase.user

import com.wonjoong.data.api.GithubApi
import com.wonjoong.data.model.GithubUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val githubApi: GithubApi
) {
    suspend fun execute(userId: String): GithubUserInfo = withContext(Dispatchers.IO) {
        githubApi.getUserInfo(userId)
    }
}