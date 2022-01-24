package com.wonjoong.gitgle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Search.route) {
        composable(route = Screen.Search.route) {

        }
        composable(route = Screen.Favorite.route) {

        }
    }
}