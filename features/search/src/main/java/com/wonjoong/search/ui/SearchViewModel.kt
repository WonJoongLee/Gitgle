package com.wonjoong.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.domain.usecase.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase
) : ViewModel() {
    val userInput = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val follower = MutableLiveData<String>()
    val following = MutableLiveData<String>()
    val createdAt = MutableLiveData<String>()

    fun searchInput() {
        viewModelScope.launch {
            val result = getUserInfo.execute(userInput.value.toString())
            name.value = result.name
            follower.value = result.followers
            following.value = result.following
            createdAt.value = result.createdAt
        }
    }
}