package com.wonjoong.data.repository

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.api.GithubApi
import com.wonjoong.data.local.GitgleDAO
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.model.GithubUserInfo
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val gitgleDAO: GitgleDAO
) : GithubRepository {
    override suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo {
        return runCatching {
            val localUserInfo = gitgleDAO.getUserByUserId(userId)
            GithubUserInfo(
                name = localUserInfo.name,
                profileUrl = localUserInfo.profileUrl,
                followers = localUserInfo.followers,
                following = localUserInfo.following,
                createdAt = localUserInfo.createdAt
            )
        }.getOrDefault(githubApi.getUserInfo(userId))
    }

    override suspend fun saveAsFavoriteUser(favoriteUserData: FavoriteUserData) {
        gitgleDAO.saveFavoriteFriend(favoriteUserData)
    }
}