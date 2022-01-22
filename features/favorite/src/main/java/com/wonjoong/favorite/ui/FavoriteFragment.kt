package com.wonjoong.favorite.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.fragment.app.viewModels
import com.google.android.material.composethemeadapter.MdcTheme
import com.wonjoong.favorite.R
import com.wonjoong.favorite.databinding.FragmentFavoriteBinding
import com.wonjoong.favorite.ui.compose.FavoritePersonItem
import com.wonjoong.shared.base.BaseFragment
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.util.makeSnackBar
import com.wonjoong.shared.util.openUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy {
        FavoriteAdapter(
            this::removeFavoriteUser,
            requireContext()::openUrl
        )
    }
    private val favoriteUserList = mutableStateListOf<FavoriteUserData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
//        initRecyclerView()
        observeFavoriteUsersList()
        binding.composeView.setContent {
            MdcTheme {
                FavoriteUserList()
            }
        }
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

//    private fun initRecyclerView() {
//        binding.rvFavoriteList.adapter = favoriteAdapter
//    }

    private fun observeFavoriteUsersList() {
        viewModel.favoriteUserList.observe(viewLifecycleOwner) { newList ->
            //favoriteAdapter.submitList(newList)
            favoriteUserList.clear()
            favoriteUserList.addAll(newList)
        }
    }

    private fun removeFavoriteUser(userId: String) {
        viewModel.removeFavoriteUser(userId)
        binding.makeSnackBar(
            getString(R.string.user_removed, userId),
            viewModel::addRecentlyRemovedUser,
            getString(R.string.cancel)
        )
    }

    @Composable
    fun FavoriteUserList() {
        LazyColumn() {
            items(favoriteUserList) { favoriteUser ->
                Text(text = "name = ${favoriteUser.name}")
            }
        }
    }
}