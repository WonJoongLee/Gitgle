package com.wonjoong.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wonjoong.shared.model.FavoriteUserData

@Dao
interface GitgleDAO {
    @Insert
    suspend fun saveFavoriteFriend(favoriteUserData: FavoriteUserData)

    @Query("SELECT * FROM FavoriteUser WHERE userId = :userId LIMIT 1")
    suspend fun getUserByUserId(userId: String): FavoriteUserData
}