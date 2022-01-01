package com.wonjoong.data

import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.model.GithubUserInfo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getGithubUserInfoOf(userId: String): GithubUserInfo

    suspend fun saveAsFavoriteUser(favoriteUserData: FavoriteUserData)

    suspend fun deleteFavoriteUser(userId: String)

    suspend fun getFavoriteUserByUserId(userId: String): FavoriteUserData

    fun getAllFavoriteUsers(): Flow<List<FavoriteUserData>>
}