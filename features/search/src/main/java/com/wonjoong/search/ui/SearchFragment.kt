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
        observeLoadingDone()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initGithubLogoClickEvent() {
        binding.ivGithubLogo.setOnClickListener {
            CustomTabsIntent.Builder()
                .build()
                .launchUrl(
                    requireContext(),
                    "https://www.github.com/${viewModel.userInput.value}".toUri()
                )
        }
    }

    private fun observeLoadingDone() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collectLatest { isLoadingDone ->
                    if (isLoadingDone) binding.hideKeyboard()
                }
            }
        }
    }
}