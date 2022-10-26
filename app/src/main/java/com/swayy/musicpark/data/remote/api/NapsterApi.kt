package com.swayy.musicpark.data.remote.api

import com.swayy.musicpark.data.remote.dto.tracks.TrackResponseDto
import com.swayy.musicpark.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NapsterApi {

    @GET("v2.2/tracks/top")
    suspend fun getTracks(
        @Query("range")range:String = "week",
        @Query("apikey")apikey:String = Constants.API_KEY
    ): TrackResponseDto


    @GET("v2.2/tracks/{tra}")
    suspend fun getTrackDetails(
        @Path("tra") trackId : String,
        @Query("apikey")apikey: String = Constants.API_KEY
    ) : TrackResponseDto
}