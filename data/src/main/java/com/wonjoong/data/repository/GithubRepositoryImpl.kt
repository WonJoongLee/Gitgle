package com.wonjoong.data.repository

import com.wonjoong.data.GithubRepository
import com.wonjoong.data.api.GithubApi
import com.wonjoong.data.local.GitgleDAO
import com.wonjoong.data.model.FavoriteUserData
import com.wonjoong.data.model.GithubUserInfo
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val gitgleDAO: GitgleDAO
) : GithubRepository {
    override suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo {
        return githubApi.getUserInfo(userId)
    }

    override suspend fun saveAsFavoriteUser(favoriteUserData: FavoriteUserData) {
        gitgleDAO.saveFavoriteFriend(favoriteUserData)
    }
}