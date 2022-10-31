package com.swayy.musicpark.data.remote.api

import com.swayy.musicpark.data.remote.dto.artist.ArtistResponseDto
import com.swayy.musicpark.data.remote.dto.playlist.PlaylistResponseDto
import com.swayy.musicpark.data.remote.dto.posts.PostsResponseDto
import com.swayy.musicpark.data.remote.dto.tracks.TrackResponseDto
import com.swayy.musicpark.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NapsterApi {
    //get tracks
    @GET("v2.2/tracks/top")
    suspend fun getTracks(
        @Query("limit") limit: String = "100",
        @Query("range") range: String = "day",
        @Query("apikey") apikey: String = Constants.API_KEY
    ): TrackResponseDto

    //get track details
    @GET("v2.2/tracks/{tra}")
    suspend fun getTrackDetails(
        @Path("tra") trackId: String,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): TrackResponseDto

    //get recommended posts
    @GET("v2.2/posts")
    suspend fun getPosts(
        @Query("range") range: String = "day",
        @Query("apikey") apikey: String = Constants.API_KEY
    ): PostsResponseDto

    //get artist
    @GET("v2.2/artists/top")
    suspend fun getArtists(
        @Query("limit") limit: String = "20",
        @Query("range") range: String = "day",
        @Query("apikey") apikey: String = Constants.API_KEY
    ): ArtistResponseDto

    //get post details
    @GET("v2.2/posts/{id}")
    suspend fun getPostDetails(
        @Path("id") id: String,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): PostsResponseDto

    //get playlist tracks
    @GET("v2.2/playlists/{id}/tracks")
    suspend fun getPlaylists(
        @Path("id") id: String,
        @Query("limit") limit: String = "50",
        @Query("apikey") apikey: String = Constants.API_KEY
    ):PlaylistResponseDto

    //get top tracks of an artist
    @GET("v2.2/artists/{id}/tracks/top")
    suspend fun getTop(
        @Path("id") id: String,
        @Query("apikey") apikey: String = Constants.API_KEY
    ) : TrackResponseDto

    //get Artist Details
    @GET("v2.2/artists/{id}")
    suspend fun getArtistDetails(
        @Path("id") id: String,
        @Query("limit") limit: String = "3",
        @Query("apikey") apikey: String = Constants.API_KEY
    ): ArtistResponseDto
}