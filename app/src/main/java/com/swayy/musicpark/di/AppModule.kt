package com.swayy.musicpark.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.swayy.musicpark.data.local.converters.Converters
import com.swayy.musicpark.data.local.db.NapsterDatabase
import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.repository.TrackRepositoryImpl
import com.swayy.musicpark.domain.repository.TrackRepository
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