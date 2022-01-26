package com.wonjoong.favorite.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.wonjoong.favorite.R
import com.wonjoong.shared.model.FavoriteUserData

private const val FADE_DURATION = 500

@Composable
@ExperimentalCoilApi
fun FavoriteScreen() {
    FavoriteUserList()
}

@Composable
@ExperimentalCoilApi
private fun FavoriteUserList(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteUserList by favoriteViewModel.getFavoriteUserFlow.collectAsState(initial = emptyList())
    LazyColumn {
        items(favoriteUserList) { favoriteUser ->
            FavoritePersonItem(favoriteUser)
        }
    }
}

@Composable
@ExperimentalCoilApi
private fun FavoritePersonItem(
    user: FavoriteUserData,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userProfileImage =
        rememberImagePainter(data = user.profileImageUrl) {
            crossfade(durationMillis = FADE_DURATION)
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
                contentDescription = stringResource(R.string.user_image),
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
                    text = stringResource(R.string.follower_info, user.followers, user.following),
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(R.string.created_at, user.createdAt),
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
                        favoriteViewModel.removeFavoriteUser(user.userId)
                    }
                    .width(34.dp)
                    .height(34.dp)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.ic_baseline_star_24),
                contentDescription = stringResource(R.string.favorite_button)
            )
            Image(
                modifier = Modifier
                    .clickable {
                        val browserIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/${user.userId}")
                        )
                        context.startActivity(browserIntent)
                    }
                    .width(34.dp)
                    .height(34.dp)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.github_logo),
                contentDescription = stringResource(R.string.personal_github_page)
            )
        }
    }
}

@Composable
@Preview
@ExperimentalCoilApi
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

//private fun removeFavoriteUser(
//    userId: String,
//) {
//    viewModel.removeFavoriteUser(userId)
////    binding.makeSnackBar(
////        stringResource(R.string.user_removed, userId),
////        viewModel::addRecentlyRemovedUser,
////        stringResource(R.string.cancel)
////    )
//}