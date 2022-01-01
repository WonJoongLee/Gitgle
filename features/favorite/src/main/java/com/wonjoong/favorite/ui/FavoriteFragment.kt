package com.wonjoong.favorite.ui

import android.os.Bundle
import android.view.View
import com.wonjoong.favorite.R
import com.wonjoong.favorite.databinding.FragmentFavoriteBinding
import com.wonjoong.shared.base.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this

    }
}