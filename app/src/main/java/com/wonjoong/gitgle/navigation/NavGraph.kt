package com.wonjoong.gitgle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wonjoong.favorite.ui.FavoriteScreen
import com.wonjoong.search.ui.SearchScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItem.Search.screen_route
    ) {
        composable(route = BottomNavigationItem.Search.screen_route) {
            SearchScreen()
        }
        composable(route = BottomNavigationItem.Favorite.screen_route) {
            FavoriteScreen()
        }
    }
}