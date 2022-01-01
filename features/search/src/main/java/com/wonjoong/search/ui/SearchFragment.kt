package com.wonjoong.search.ui

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.wonjoong.search.R
import com.wonjoong.search.databinding.FragmentSearchBinding
import com.wonjoong.shared.base.BaseFragment
import com.wonjoong.shared.util.hideKeyboard
import com.wonjoong.shared.util.openUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initGithubLogoClickEvent()
        collectSearchUiState()
        collectFavoriteClickedUiState()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initGithubLogoClickEvent() {
        binding.ivGithubLogo.setOnClickListener {
            requireContext().openUrl("https://www.github.com/${viewModel.userInput.value}")
        }
    }

    private fun collectSearchUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchUiState.collectLatest { newSearchUiState ->
                    when (newSearchUiState) {
                        SearchViewModel.SearchUiState.Found -> {
                            binding.groupResult.visibility = View.VISIBLE
                            binding.tvNotFound.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                        }
                        SearchViewModel.SearchUiState.NotFound -> {
                            binding.groupResult.visibility = View.GONE
                            binding.tvNotFound.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                        }
                        SearchViewModel.SearchUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.groupResult.visibility = View.GONE
                            binding.tvNotFound.visibility = View.GONE
                            binding.hideKeyboard()
                        }
                        SearchViewModel.SearchUiState.Empty -> {
                            binding.progressBar.visibility = View.GONE
                            binding.groupResult.visibility = View.GONE
                            binding.tvNotFound.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun collectFavoriteClickedUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteClickedUiState.collectLatest { newEnabledState ->
                    when (newEnabledState) {
                        SearchViewModel.FavoriteClickState.Enabled -> {
                            binding.ivStar.setBackgroundResource(R.drawable.ic_baseline_star_24)
                        }
                        SearchViewModel.FavoriteClickState.Disabled, SearchViewModel.FavoriteClickState.Empty -> {
                            binding.ivStar.setBackgroundResource(R.drawable.ic_baseline_star_outline_24)
                        }
                    }
                }
            }
        }
    }
}