package com.wonjoong.data

import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.model.GithubUserInfo

interface GithubRepository {
    suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo

    suspend fun saveAsFavoriteUser(favoriteUserData: FavoriteUserData)
}