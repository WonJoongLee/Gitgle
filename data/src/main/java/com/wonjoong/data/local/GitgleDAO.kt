package com.wonjoong.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.wonjoong.data.model.FavoriteUserData

@Dao
interface GitgleDAO {
    @Insert
    suspend fun saveFavoriteFriend(favoriteUserData: FavoriteUserData)
}