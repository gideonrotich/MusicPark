package com.swayy.musicpark.data.remote.mapper

import com.swayy.musicpark.data.local.entity.TrackEntity
import com.swayy.musicpark.data.remote.dto.tracks.Track

internal fun Track.toEntity(): TrackEntity{
    return TrackEntity(albumId,albumName,albumId,artistName,blurbs,contributors, disc, formats, href, id, index, isAvailableInAtmos, isAvailableInHiRes, isExplicit, isStreamable, isrc, links, losslessFormats, name, playbackSeconds, previewURL, shortcut, type)
}

internal fun Track.toDomain():com.swayy.musicpark.domain.models.Track{
    return com.swayy.musicpark.domain.models.Track(albumId, albumName, artistId, artistName, blurbs, contributors, disc, formats, href, id, index, isAvailableInAtmos, isAvailableInHiRes, isExplicit, isStreamable, isrc, links, losslessFormats, name, playbackSeconds, previewURL, shortcut, type)
}