package com.swayy.musicpark.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.swayy.musicpark.data.local.converters.Converters
import com.swayy.musicpark.data.local.db.NapsterDatabase
import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.repository.*
import com.swayy.musicpark.domain.repository.*
import com.swayy.musicpark.domain.use_cases.*
import com.swayy.musicpark.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideConverters(gson: Gson) = Converters(gson)

    @Provides
    @Singleton
    fun provideNapsterDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): NapsterDatabase {
        return Room.databaseBuilder(
            context,
            NapsterDatabase::class.java,
            "napster_db"
        )
            .addTypeConverter(converters)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTrackRepository(
        napsterApi: NapsterApi,
        napsterDatabase: NapsterDatabase
    ): TrackRepository {
        return TrackRepositoryImpl(
            napsterApi = napsterApi,
            trackDao = napsterDatabase.TrackDao()
        )
    }

    @Provides
    @Singleton
    fun providesTrackDetailsRepository(
        napsterApi: NapsterApi
    ): GetTrackDetailsRepository {
        return GetTrackDetailsRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Provides
    @Singleton
    fun providesPostDetailsRepository(
        napsterApi: NapsterApi
    ): PostDetailsRepository {
        return PostDetailsRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Provides
    @Singleton
    fun providesPostsRepository(
        napsterApi: NapsterApi
    ):getPosts{
        return PostRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Provides
    @Singleton
    fun providesArtistRepository(
        napsterApi: NapsterApi
    ):ArtistRepository{
        return ArtistRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Provides
    @Singleton
    fun providesPlaylistRepository(
        napsterApi: NapsterApi
    ):PlaylistRepository{
        return PlaylistRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Provides
    @Singleton
    fun providesTopRepository(
        napsterApi: NapsterApi
    ):TopRepository{
        return TopRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Provides
    @Singleton
    fun providesArtistDetailsRepository(
        napsterApi: NapsterApi
    ):ArtistDetailsRepository{
        return ArtistDetailsRepositoryImpl(
            napsterApi = napsterApi
        )
    }

    @Singleton
    @Provides
    fun provideGetTrackDetailsUseCase(
        getTrackRepository: GetTrackDetailsRepository
    ): GetTrackDetailsUseCase {
        return GetTrackDetailsUseCase(getTrackRepository)
    }

    @Singleton
    @Provides
    fun provideGetTopUseCase(
        topRepository: TopRepository
    ): GetTopUseCase {
        return GetTopUseCase(topRepository)
    }

    @Singleton
    @Provides
    fun provideGetArtistDetailsUseCase(
        artistDetailsRepository: ArtistDetailsRepository
    ): GetArtistDetailsUseCase {
        return GetArtistDetailsUseCase(artistDetailsRepository)
    }

    @Singleton
    @Provides
    fun provideGetPostDetailsUseCase(
        postDetailsRepository: PostDetailsRepository
    ): GetPostDetailsUseCase {
        return GetPostDetailsUseCase(postDetailsRepository)
    }

    @Singleton
    @Provides
    fun provideGetPlaylistUseCase(
        playlistRepository: PlaylistRepository
    ): GetPlaylistsUseCase {
        return GetPlaylistsUseCase(playlistRepository)
    }

    @Singleton
    @Provides
    fun providesGetPostsUseCase(
        getPosts: getPosts
    ):GetPostsUseCase{
        return GetPostsUseCase(getPosts)
    }

    @Singleton
    @Provides
    fun providesGetArtistUseCase(
        artistRepository: ArtistRepository
    ):GetArtistUseCase{
        return GetArtistUseCase(artistRepository)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideNapsterApi(okHttpClient: OkHttpClient): NapsterApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NapsterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        return okHttpClient.build()
    }
}