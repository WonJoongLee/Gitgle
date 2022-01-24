package com.wonjoong.gitgle.navigation

import com.wonjoong.gitgle.R

sealed class BottomNavigationItem(val title: String, val icon: Int, val screen_route: String) {
    object Search: BottomNavigationItem("Search", R.drawable.ic_baseline_search_24, "search")
    object Favorite: BottomNavigationItem("Favorite", R.drawable.ic_baseline_star_24, "favorite")
}
