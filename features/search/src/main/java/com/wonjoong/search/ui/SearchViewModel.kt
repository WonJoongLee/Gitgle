package com.wonjoong.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.data.model.GithubUserInfo
import com.wonjoong.domain.usecase.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// todo UIState(StateFlow)로 업데이트하기
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase
) : ViewModel() {
    val userInput = MutableLiveData<String>()
    val profileImageUrl = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val follower = MutableLiveData<String>()
    val following = MutableLiveData<String>()
    val createdAt = MutableLiveData<String>()
    val isLoading = MutableStateFlow(false)
    val dataCategoryVisibility = MutableStateFlow(false)
    val isUserRepoNotFound = MutableStateFlow(false)

    fun searchInput() {
        isLoading.value = true
        dataCategoryVisibility.value = false
        viewModelScope.launch {
            runCatching {
                getUserInfo.execute(userInput.value.toString())
            }.onSuccess { githubUserInfo ->
                isLoading.value = false
                bindResultData(githubUserInfo)
            }.onFailure {
                isLoading.value = false
                isUserRepoNotFound.value = true
            }
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
}