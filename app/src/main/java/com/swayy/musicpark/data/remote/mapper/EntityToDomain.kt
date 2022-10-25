package com.swayy.musicpark.data.remote.mapper

import com.swayy.musicpark.data.local.entity.TrackEntity
import com.swayy.musicpark.domain.models.Track

internal fun TrackEntity.toDomain():Track{
    return Track(albumId, albumName, artistId, artistName, blurbs, contributors, disc, formats, href, id, index, isAvailableInAtmos, isAvailableInHiRes, isExplicit, isStreamable, isrc, links, losslessFormats, name, playbackSeconds, previewURL, shortcut, type)
}