package com.wonjoong.gitgle.navigation

sealed class Screen(val route: String) {
    object Search : Screen("search_screen")
    object Favorite : Screen("favorite_screen")
}