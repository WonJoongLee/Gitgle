package com.wonjoong.favorite.ui

import android.util.Log
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
    val temp = getAlLFavoriteUsersUseCase


    init {
        getFavoriteUserList()
    }

    fun removeFavoriteUser(userId: String) {
        viewModelScope.launch {
            recentlyRemovedUser = getFavoriteUserUseCase.execute(userId)
            removeFavoriteUserUseCase.execute(userId)
        }
    }

    fun addRecentlyRemovedUser() {
        viewModelScope.launch {
            saveFavoriteUserUseCase.execute(recentlyRemovedUser)
        }
    }

    private fun getFavoriteUserList() {
        viewModelScope.launch {
            getAlLFavoriteUsersUseCase.execute().collectLatest { newList ->
                Log.e("newList", ".$newList")
                _favoriteUserList.value = newList
            }
        }
    }
}