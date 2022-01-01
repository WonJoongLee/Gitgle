package com.wonjoong.favorite.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.favorite.R
import com.wonjoong.favorite.databinding.ItemGithubUserBinding
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.util.binding

class FavoriteAdapter(private val removeFavoriteUser: (String) -> Unit) :
    ListAdapter<FavoriteUserData, FavoriteAdapter.FavoriteUserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        return FavoriteUserViewHolder(parent.binding(R.layout.item_github_user), removeFavoriteUser)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class FavoriteUserViewHolder(
        private val binding: ItemGithubUserBinding,
        private val removeFavoriteUser: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var userId: String

        init {
            binding.ivStar.setOnClickListener {
                if (this::userId.isInitialized) removeFavoriteUser.invoke(userId)
            }
        }

        fun bind(userData: FavoriteUserData) {
            binding.userData = userData
            userId = userData.userId
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