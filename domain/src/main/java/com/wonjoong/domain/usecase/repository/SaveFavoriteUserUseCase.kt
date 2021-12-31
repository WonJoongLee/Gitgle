package com.wonjoong.domain.usecase.repository

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.model.FavoriteUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveFavoriteUserUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun execute(favoriteUserData: FavoriteUserData) = withContext(Dispatchers.IO) {
        githubRepository.saveAsFavoriteUser(favoriteUserData)
    }
}