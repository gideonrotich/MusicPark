/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.swayy.musicpark.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object TrackDetails : Screen("track_details_screen")
    object AllTracks : Screen("all_tracks")
    object PostDetails : Screen("post_details_screen")
    object ArtistDetails : Screen("artists_details")
    object AlbumDetails : Screen("album_details")
    object SearchScreen : Screen("search_screen")
    object LoginScreen : Screen("login_screen")
}
