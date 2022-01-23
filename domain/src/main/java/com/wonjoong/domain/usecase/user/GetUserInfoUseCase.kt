package com.wonjoong.domain.usecase.user

import com.wonjoong.data.GithubRepository
import com.wonjoong.shared.model.GithubUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(userId: String): GithubUserInfo = withContext(Dispatchers.IO) {
        githubRepository.getGithubUserInfoOf(userId)
    }
}