package com.swayy.musicpark.data.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.swayy.musicpark.data.local.db.NapsterDatabase
import com.google.common.truth.Truth.assertThat
import com.swayy.musicpark.data.local.entity.TrackEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TrackDaoTest {
    private lateinit var database : NapsterDatabase
    private lateinit var trackDao: TrackDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, NapsterDatabase::class.java)
            .allowMainThreadQueries().build()
        trackDao = database.TrackDao()
    }

    @After
    fun tearDown() {
        runBlocking {
            trackDao.deleteTracks()
            database.close()
        }
    }










}