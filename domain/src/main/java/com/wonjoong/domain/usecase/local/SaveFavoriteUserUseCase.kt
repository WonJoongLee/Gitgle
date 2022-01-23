package com.wonjoong.domain.usecase.local

import com.wonjoong.data.GithubRepository
import com.wonjoong.shared.model.FavoriteUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveFavoriteUserUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(favoriteUserData: FavoriteUserData) = withContext(Dispatchers.IO) {
        githubRepository.saveAsFavoriteUser(favoriteUserData)
    }
}