package com.wonjoong.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wonjoong.shared.model.FavoriteUserData
import kotlinx.coroutines.flow.Flow

@Dao
interface GitgleDAO {
    @Insert
    suspend fun saveFavoriteFriend(favoriteUserData: FavoriteUserData)

    @Query("SELECT * FROM FavoriteUser WHERE userId = :userId LIMIT 1")
    suspend fun getFavoriteUserByUserId(userId: String): FavoriteUserData

    @Query("DELETE FROM FavoriteUser WHERE userId = :userId")
    suspend fun deleteUserByUserId(userId: String)

    @Query("SELECT * FROM FavoriteUser")
    fun getAllFavoriteUsers(): Flow<List<FavoriteUserData>>
}