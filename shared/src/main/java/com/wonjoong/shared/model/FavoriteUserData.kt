package com.wonjoong.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteUser")
data class FavoriteUserData(
    val name: String,
    val profileUrl: String,
    val followers: String,
    val following: String,
    val createdAt: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
