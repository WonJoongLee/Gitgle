package com.wonjoong.favorite.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wonjoong.favorite.R
import com.wonjoong.favorite.databinding.FragmentFavoriteBinding
import com.wonjoong.shared.base.BaseFragment
import com.wonjoong.shared.util.makeSnackBar
import com.wonjoong.shared.util.openUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy {
        FavoriteAdapter(
            this::removeFavoriteUser,
            requireContext()::openUrl
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initRecyclerView()
        observeFavoriteUsersList()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initRecyclerView() {
        binding.rvFavoriteList.adapter = favoriteAdapter
    }

    private fun observeFavoriteUsersList() {
        viewModel.favoriteUserList.observe(viewLifecycleOwner) { newList ->
            favoriteAdapter.submitList(newList)
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
}