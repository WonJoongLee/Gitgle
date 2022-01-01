package com.wonjoong.domain.usecase.local

import com.wonjoong.data.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoriteUserUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun execute(userId: String) = withContext(Dispatchers.IO) {
        githubRepository.getFavoriteUserByUserId(userId)
    }
}