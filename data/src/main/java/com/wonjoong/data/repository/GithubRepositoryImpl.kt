package com.wonjoong.data.repository

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.api.GithubApi
import com.wonjoong.data.local.GitgleDAO
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.model.GithubUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val gitgleDAO: GitgleDAO
) : GithubRepository {
    override suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo {
        return runCatching {
            val localFavoriteUserInfo = gitgleDAO.getUserByUserId(userId)
            GithubUserInfo(
                name = localFavoriteUserInfo.name,
                profileUrl = localFavoriteUserInfo.profileImageUrl,
                followers = localFavoriteUserInfo.followers,
                following = localFavoriteUserInfo.following,
                createdAt = localFavoriteUserInfo.createdAt,
                isFavorite = true
            )
        }.getOrDefault(githubApi.getUserInfo(userId))
    }

    override suspend fun saveAsFavoriteUser(favoriteUserData: FavoriteUserData) {
        gitgleDAO.saveFavoriteFriend(favoriteUserData)
    }

    override suspend fun deleteFavoriteUser(userId: String) {
        gitgleDAO.deleteUserByUserId(userId)
    }

    override fun getAllFavoriteUsers(): Flow<List<FavoriteUserData>> =
        gitgleDAO.getAllFavoriteUsers().flowOn(Dispatchers.IO).conflate()
}