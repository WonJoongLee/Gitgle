package com.wonjoong.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wonjoong.data.model.FavoriteUserData

@Database(entities = [FavoriteUserData::class], version = 1)
abstract class GitgleDatabase : RoomDatabase() {
    abstract fun gitgleDAO(): GitgleDAO
}