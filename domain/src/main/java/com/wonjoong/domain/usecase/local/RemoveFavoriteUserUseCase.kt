package com.wonjoong.domain.usecase.local

import com.wonjoong.data.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveFavoriteUserUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(userId: String) = withContext(Dispatchers.IO) {
        githubRepository.deleteFavoriteUser(userId)
    }
}