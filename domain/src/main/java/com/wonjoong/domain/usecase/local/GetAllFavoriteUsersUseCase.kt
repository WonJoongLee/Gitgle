package com.wonjoong.domain.usecase.local

import com.wonjoong.data.GithubRepository
import com.wonjoong.shared.model.FavoriteUserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteUsersUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(): Flow<List<FavoriteUserData>> = githubRepository.getAllFavoriteUsers()
}