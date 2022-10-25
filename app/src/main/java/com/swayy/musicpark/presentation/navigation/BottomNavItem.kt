package com.example.livescore.presentation.navigation

import com.swayy.musicpark.R


sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_baseline_home_24, "home")
    object Search : BottomNavItem("Search", R.drawable.ic_baseline_search_24, "standings")
    object YourLibrary : BottomNavItem("Your Library", R.drawable.ic_baseline_queue_music_24, "TopScorers")
}
