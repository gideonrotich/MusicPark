package com.example.livescore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swayy.musicpark.presentation.screens.tracks.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navController = navController)
        }
        composable(BottomNavItem.Search.screen_route) {
            HomeScreen(navController = navController)
        }
        composable(BottomNavItem.YourLibrary.screen_route) {
            HomeScreen(navController = navController)
        }

    }
}
