package com.wonjoong.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.domain.usecase.local.RemoveFavoriteUserUseCase
import com.wonjoong.domain.usecase.local.SaveFavoriteUserUseCase
import com.wonjoong.domain.usecase.user.GetUserInfoUseCase
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.model.GithubUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase,
    private val saveFavoriteUserUseCase: SaveFavoriteUserUseCase,
    private val deleteFavoriteUserUseCase: RemoveFavoriteUserUseCase
) : ViewModel() {
    val userInput = MutableLiveData<String>()
    val profileImageUrl = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val follower = MutableLiveData<String>()
    val following = MutableLiveData<String>()
    val createdAt = MutableLiveData<String>()
    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val searchUiState = _searchUiState.asStateFlow()
    private val _favoriteClickedUiState =
        MutableStateFlow<FavoriteClickState>(FavoriteClickState.Empty)
    val favoriteClickedUiState = _favoriteClickedUiState.asStateFlow()

    fun searchInput() {
        _searchUiState.value = SearchUiState.Loading
        viewModelScope.launch {
            runCatching {
                getUserInfo(userInput.value.toString())
            }.onSuccess { githubUserInfo ->
                _favoriteClickedUiState.value =
                    if (githubUserInfo.isFavorite) FavoriteClickState.Enabled else FavoriteClickState.Disabled
                _searchUiState.value = SearchUiState.Found
                bindResultData(githubUserInfo)
            }.onFailure {
                _searchUiState.value = SearchUiState.NotFound
            }
        }
    }

    fun saveAsFavoriteFriend() {
        viewModelScope.launch {
            val currentClickedState = _favoriteClickedUiState.value
            if (currentClickedState == FavoriteClickState.Disabled) {
                saveFavoriteUserUseCase(
                    FavoriteUserData(
                        userId = userInput.value ?: "-",
                        name = name.value ?: "-",
                        profileImageUrl = profileImageUrl.value ?: "-",
                        followers = follower.value ?: "-",
                        following = following.value ?: "-",
                        createdAt = createdAt.value ?: "-",
                        isFavorite = true
                    )
                )
            } else {
                deleteFavoriteUserUseCase(userInput.value.toString())
            }
            _favoriteClickedUiState.value =
                if (currentClickedState == FavoriteClickState.Disabled || currentClickedState == FavoriteClickState.Empty) FavoriteClickState.Enabled else FavoriteClickState.Disabled
        }
    }

    private fun bindResultData(githubUserInfo: GithubUserInfo) {
        profileImageUrl.value = githubUserInfo.profileUrl ?: "-"
        name.value = githubUserInfo.name ?: "-"
        follower.value = githubUserInfo.followers ?: "-"
        following.value = githubUserInfo.following ?: "-"
        createdAt.value = githubUserInfo.createdAt?.substringBefore("T") ?: "-"
    }

    sealed class SearchUiState {
        object Found : SearchUiState()
        object NotFound : SearchUiState()
        object Loading : SearchUiState()
        object Empty : SearchUiState()
    }

    sealed class FavoriteClickState {
        object Enabled : FavoriteClickState()
        object Disabled : FavoriteClickState()
        object Empty : FavoriteClickState()
    }
}