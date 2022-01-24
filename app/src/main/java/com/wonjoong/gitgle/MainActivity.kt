package com.wonjoong.gitgle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wonjoong.gitgle.navigation.BottomNavigationItem
import com.wonjoong.gitgle.navigation.SetupNavGraph
import com.wonjoong.gitgle.ui.theme.GitgleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitgleTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { MainBottomNavigation(navController = navController) }) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }

    @Composable
    fun MainBottomNavigation(navController: NavHostController) {
        val bottomNavigationItems = listOf(
            BottomNavigationItem.Search,
            BottomNavigationItem.Favorite
        )
        BottomNavigation(
            contentColor = Color.Blue,
            backgroundColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            bottomNavigationItems.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(text = item.title, fontSize = 10.sp)
                    },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray,
                    alwaysShowLabel = false
                )
            }
        }
    }
}
