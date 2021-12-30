package com.wonjoong.search.ui

import androidx.lifecycle.ViewModel
import com.wonjoong.data.api.GithubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubApi: GithubApi
) : ViewModel() {

}