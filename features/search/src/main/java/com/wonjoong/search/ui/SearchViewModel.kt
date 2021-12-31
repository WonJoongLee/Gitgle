package com.wonjoong.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.data.model.FavoriteUserData
import com.wonjoong.data.model.GithubUserInfo
import com.wonjoong.domain.usecase.repository.SaveFavoriteUserUseCase
import com.wonjoong.domain.usecase.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase,
    private val saveFavoriteUserUseCase: SaveFavoriteUserUseCase
) : ViewModel() {
    val userInput = MutableLiveData<String>()
    val profileImageUrl = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val follower = MutableLiveData<String>()
    val following = MutableLiveData<String>()
    val createdAt = MutableLiveData<String>()
    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val searchUiState = _searchUiState.asStateFlow()
    val isLoading = MutableStateFlow(false)
    val dataCategoryVisibility = MutableStateFlow(false)
    val isUserRepoNotFound = MutableStateFlow(false)

    fun searchInput() {
        _searchUiState.value = SearchUiState.Loading
        viewModelScope.launch {
            runCatching {
                getUserInfo.execute(userInput.value.toString())
            }.onSuccess { githubUserInfo ->
                _searchUiState.value = SearchUiState.Found
                bindResultData(githubUserInfo)
            }.onFailure {
                _searchUiState.value = SearchUiState.NotFound
                isLoading.value = false
                isUserRepoNotFound.value = true
            }
        }
    }

    fun saveAsFavoriteFriend() {
        viewModelScope.launch {
            saveFavoriteUserUseCase.execute(
                FavoriteUserData(
                    name = name.value ?: "-",
                    profileUrl = profileImageUrl.value ?: "-",
                    followers = follower.value ?: "-",
                    following = following.value ?: "-",
                    createdAt = createdAt.value ?: "-",
                )
            )
        }
    }

    private fun bindResultData(githubUserInfo: GithubUserInfo) {
        isUserRepoNotFound.value = false
        isLoading.value = false
        dataCategoryVisibility.value = true
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
}