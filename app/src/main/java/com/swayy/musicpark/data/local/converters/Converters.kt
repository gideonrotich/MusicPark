package com.swayy.musicpark.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swayy.musicpark.data.remote.dto.tracks.Contributors
import com.swayy.musicpark.data.remote.dto.tracks.Format
import com.swayy.musicpark.data.remote.dto.tracks.Links
import com.swayy.musicpark.data.remote.dto.tracks.LosslessFormat

@ProvidedTypeConverter
class Converters(
    private val gson: Gson
) {
    @TypeConverter
    fun listOfStringToString(str: List<String>?): String? {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun strToListString(str: String?): List<String>? {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }

    // contributor converter
    @TypeConverter
    fun fromContributor(str: Contributors): String {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toContributor(str: String): Contributors {
        return Gson().fromJson(str, object : TypeToken<String>() {}.type)
    }

    // format converter
    @TypeConverter
    fun fromFormat(str: List<Format>?): String? {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toFormat(str: String?): List<Format>? {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromLink(str: Links): String? {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toLink(str: String?): Links {
        return Gson().fromJson(str, object : TypeToken<String>() {}.type)
    }

    // lossformat converter
    @TypeConverter
    fun fromLossFormat(str: List<LosslessFormat>?): String? {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toLossFormat(str: String?): List<LosslessFormat>? {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }
}