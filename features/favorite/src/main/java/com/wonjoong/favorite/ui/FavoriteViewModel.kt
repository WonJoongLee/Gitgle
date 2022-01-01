package com.wonjoong.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.domain.usecase.local.GetAllFavoriteUsersUseCase
import com.wonjoong.domain.usecase.local.RemoveFavoriteUserUseCase
import com.wonjoong.shared.model.FavoriteUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAlLFavoriteUsersUseCase: GetAllFavoriteUsersUseCase,
    private val removeFavoriteUserUseCase: RemoveFavoriteUserUseCase
) : ViewModel() {
    private val _favoriteUserList = MutableLiveData<List<FavoriteUserData>>()
    val favoriteUserList: LiveData<List<FavoriteUserData>> get() = _favoriteUserList

    init {
        getFavoriteUserList()
    }

    fun removeFavoriteUser(userId: String) {
        viewModelScope.launch {
            removeFavoriteUserUseCase.execute(userId)
        }
    }

    private fun getFavoriteUserList() {
        viewModelScope.launch {
            getAlLFavoriteUsersUseCase.execute().collectLatest { newList ->
                _favoriteUserList.value = newList
            }
        }
    }
}