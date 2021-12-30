package com.wonjoong.domain.usecase.user

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.api.GithubApi
import com.wonjoong.data.model.GithubUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun execute(userId: String): GithubUserInfo = withContext(Dispatchers.IO) {
        githubRepository.getGithubUserInfoOf(userId)
    }
}