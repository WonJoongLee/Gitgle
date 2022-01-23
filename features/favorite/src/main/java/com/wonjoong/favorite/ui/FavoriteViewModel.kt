package com.wonjoong.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.domain.usecase.local.GetAllFavoriteUsersUseCase
import com.wonjoong.domain.usecase.local.GetFavoriteUserUseCase
import com.wonjoong.domain.usecase.local.RemoveFavoriteUserUseCase
import com.wonjoong.domain.usecase.local.SaveFavoriteUserUseCase
import com.wonjoong.shared.model.FavoriteUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getAlLFavoriteUsersUseCase: GetAllFavoriteUsersUseCase,
    private val removeFavoriteUserUseCase: RemoveFavoriteUserUseCase,
    private val getFavoriteUserUseCase: GetFavoriteUserUseCase,
    private val saveFavoriteUserUseCase: SaveFavoriteUserUseCase
) : ViewModel() {
    private lateinit var recentlyRemovedUser: FavoriteUserData
    val getFavoriteUserFlow = getAlLFavoriteUsersUseCase()

    fun removeFavoriteUser(userId: String) {
        viewModelScope.launch {
            recentlyRemovedUser = getFavoriteUserUseCase(userId)
            removeFavoriteUserUseCase(userId)
        }
    }

    fun addRecentlyRemovedUser() {
        viewModelScope.launch {
            saveFavoriteUserUseCase(recentlyRemovedUser)
        }
    }
}