package com.wonjoong.search.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wonjoong.search.R
import com.wonjoong.search.databinding.FragmentSearchBinding
import com.wonjoong.shared.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}