package com.example.livescore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.presentation.Screen
import com.swayy.musicpark.presentation.screens.artist.ArtistDetailsScreen
import com.swayy.musicpark.presentation.screens.artist.components.AlbumDetailScreen
import com.swayy.musicpark.presentation.screens.postdetails.PostDetailsScreen
import com.swayy.musicpark.presentation.screens.profile.ProfileScreen
import com.swayy.musicpark.presentation.screens.search.SearchScreen
import com.swayy.musicpark.presentation.screens.tracks.AllTracksScreen
import com.swayy.musicpark.presentation.screens.tracks.HomeScreen
import com.swayy.musicpark.presentation.screens.tracksDetails.TrackDetailsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navController = navController)
        }
        composable(BottomNavItem.Search.screen_route) {
            SearchScreen(navController = navController)
        }
        composable(BottomNavItem.YourLibrary.screen_route) {
            ProfileScreen(navController = navController)
        }

        composable(
            route = Screen.TrackDetails.route + "/{id}"
        ){
            TrackDetailsScreen()
        }
        composable(
            route = Screen.PostDetails.route + "/{id}"
        ){
            PostDetailsScreen(navController = navController)
        }

        composable(
            route = Screen.AlbumDetails.route + "/{id}"
        ){
            AlbumDetailScreen(navController = navController)
        }

        composable(
            route = Screen.ArtistDetails.route + "/{id}"
        ){
            ArtistDetailsScreen(navController = navController)
        }

        composable(
            route = Screen.AllTracks.route
        ){
            AllTracksScreen(navController = navController)
        }

    }
}
