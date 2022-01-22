package com.wonjoong.favorite.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.android.material.composethemeadapter.MdcTheme
import com.wonjoong.favorite.R
import com.wonjoong.favorite.databinding.FragmentFavoriteBinding
import com.wonjoong.shared.base.BaseFragment
import com.wonjoong.shared.model.FavoriteUserData
import com.wonjoong.shared.util.makeSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteUserList = mutableStateListOf<FavoriteUserData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initComposeView()
        collectFavoriteFriendList()
    }

    private fun initComposeView() {
        binding.composeView.setContent {
            MdcTheme {
                FavoriteUserList()
            }
        }
    }

    private fun collectFavoriteFriendList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getFavoriteUserFlow.collectLatest { newList ->
                    favoriteUserList.clear()
                    favoriteUserList.addAll(newList)
                }
            }
        }
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
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
    private fun FavoriteUserList() {
        LazyColumn {
            items(favoriteUserList) { favoriteUser ->
                FavoritePersonItem(favoriteUser)
            }
        }
    }

    @Composable
    private fun FavoritePersonItem(user: FavoriteUserData) {
        val userProfileImage =
            rememberImagePainter(data = user.profileImageUrl) {
                crossfade(durationMillis = 500)
                error(R.drawable.ic_baseline_person_off_24)
                placeholder(R.drawable.ic_baseline_person_24)
            }
        Box(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .background(Color.White),
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 8.dp)
                        .height(120.dp)
                        .width(120.dp),
                    painter = userProfileImage,
                    contentDescription = "User Image",
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = user.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Follower : ${user.followers}, Following : ${user.following}",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Created at ${user.createdAt}",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }
            Row(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                    modifier = Modifier
                        .clickable {
                            removeFavoriteUser(user.userId)
                        }
                        .width(34.dp)
                        .height(34.dp)
                        .padding(4.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_star_24),
                    contentDescription = "Favorite Button"
                )
                Image(
                    modifier = Modifier
                        .clickable {
                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/${user.userId}")
                            )
                            startActivity(browserIntent)
                        }
                        .width(34.dp)
                        .height(34.dp)
                        .padding(4.dp),
                    painter = painterResource(id = R.drawable.github_logo),
                    contentDescription = "Personal github page"
                )
            }
        }
    }

    @Composable
    @Preview
    private fun ItemPreview() {
        FavoritePersonItem(
            user = FavoriteUserData(
                "WonJoong",
                "WonJoongLee",
                "https://avatars.githubusercontent.com/u/57510192?v=4",
                "37",
                "31",
                "2019-11-08",
                true
            )
        )
    }
}