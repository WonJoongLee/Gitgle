package com.wonjoong.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteUser")
data class FavoriteUserData(
    val userId: String,
    val name: String,
    val profileUrl: String,
    val followers: String,
    val following: String,
    val createdAt: String,
    var isFavorite: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
