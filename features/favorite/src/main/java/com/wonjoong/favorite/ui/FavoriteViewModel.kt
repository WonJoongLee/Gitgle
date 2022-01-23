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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAlLFavoriteUsersUseCase: GetAllFavoriteUsersUseCase,
    private val removeFavoriteUserUseCase: RemoveFavoriteUserUseCase,
    private val getFavoriteUserUseCase: GetFavoriteUserUseCase,
    private val saveFavoriteUserUseCase: SaveFavoriteUserUseCase
) : ViewModel() {
    private val _favoriteUserList = MutableLiveData<List<FavoriteUserData>>()
    val favoriteUserList: LiveData<List<FavoriteUserData>> get() = _favoriteUserList
    private lateinit var recentlyRemovedUser: FavoriteUserData


    init {
        getFavoriteUserList()
    }

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

    private fun getFavoriteUserList() {
        viewModelScope.launch {
            getAlLFavoriteUsersUseCase().collectLatest { newList ->
                _favoriteUserList.value = newList
            }
        }
    }
}