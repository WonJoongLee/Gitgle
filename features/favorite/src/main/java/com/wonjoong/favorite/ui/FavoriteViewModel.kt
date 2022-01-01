package com.wonjoong.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.shared.model.FavoriteUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(

): ViewModel() {
    private val _favoriteUserList = MutableLiveData<List<FavoriteUserData>>()
    val favoriteUserList: LiveData<List<FavoriteUserData>> get() = _favoriteUserList

    init {
        getFavoriteUserList()
    }

    private fun getFavoriteUserList() {

    }
}