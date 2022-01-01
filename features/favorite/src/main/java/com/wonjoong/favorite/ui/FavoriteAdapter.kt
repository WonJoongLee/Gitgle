package com.wonjoong.favorite.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.favorite.R
import com.wonjoong.favorite.databinding.ItemGithubUserBinding
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.util.binding

class FavoriteAdapter :
    ListAdapter<FavoriteUserData, FavoriteAdapter.FavoriteUserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        return FavoriteUserViewHolder(parent.binding(R.layout.item_github_user))
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class FavoriteUserViewHolder(private val binding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: FavoriteUserData) {
            binding.userData = userData
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteUserData>() {
            override fun areItemsTheSame(
                oldItem: FavoriteUserData,
                newItem: FavoriteUserData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteUserData,
                newItem: FavoriteUserData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}