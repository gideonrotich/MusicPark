package com.swayy.musicpark.data.remote.mapper

import com.swayy.musicpark.data.local.entity.TrackEntity
import com.swayy.musicpark.data.remote.dto.album.Album
import com.swayy.musicpark.data.remote.dto.artist.Artist
import com.swayy.musicpark.data.remote.dto.posts.Post
import com.swayy.musicpark.data.remote.dto.tracks.Track
import com.swayy.musicpark.domain.models.Playlist

internal fun Track.toEntity(): TrackEntity {
    return TrackEntity(
        albumId,
        albumName,
        albumId,
        artistName,
        blurbs,
        contributors,
        disc,
        formats,
        href,
        id,
        index,
        isAvailableInAtmos,
        isAvailableInHiRes,
        isExplicit,
        isStreamable,
        isrc,
        links,
        losslessFormats,
        name,
        playbackSeconds,
        previewURL,
        shortcut,
        type
    )
}

internal fun Track.toDomain(): com.swayy.musicpark.domain.models.Track {
    return com.swayy.musicpark.domain.models.Track(
        albumId,
        albumName,
        artistId,
        artistName,
        blurbs,
        contributors,
        disc,
        formats,
        href,
        id,
        index,
        isAvailableInAtmos,
        isAvailableInHiRes,
        isExplicit,
        isStreamable,
        isrc,
        links,
        losslessFormats,
        name,
        playbackSeconds,
        previewURL,
        shortcut,
        type
    )
}

internal fun Post.toDomain(): com.swayy.musicpark.domain.models.Post {
    return com.swayy.musicpark.domain.models.Post(
        author,
        content,
        date,
        href,
        id,
        image,
        language,
        links,
        name,
        primaryMedia,
        shortcut,
        subType,
        type
    )
}

internal fun Artist.toDomain(): com.swayy.musicpark.domain.models.Artist {
    return com.swayy.musicpark.domain.models.Artist(
        albumGroups,
        amg,
        bios,
        blurbs,
        href,
        id,
        links,
        name,
        shortcut,
        type
    )
}

internal fun com.swayy.musicpark.data.remote.dto.playlist.Track.toDomain(): Playlist {
    return Playlist(
        albumId,
        albumName,
        amg,
        artistId,
        artistName,
        blurbs,
        contributors,
        disc,
        formats,
        href,
        id,
        index,
        isAvailableInAtmos,
        isAvailableInHiRes,
        isExplicit,
        isStreamable,
        isrc,
        links,
        losslessFormats,
        name,
        playbackSeconds,
        previewURL,
        shortcut,
        type
    )
}

internal fun Album.toDomain(): com.swayy.musicpark.domain.models.Album {
    return com.swayy.musicpark.domain.models.Album(
        accountPartner,
        amg,
        artistName,
        contributingArtists,
        copyright,
        discCount,
        discographies,
        href,
        id,
        isAvailableInAtmos,
        isAvailableInHiRes,
        isExplicit,
        isSingle,
        isStreamable,
        label,
        links,
        name,
        originallyReleased,
        released,
        shortcut,
        tags,
        trackCount,
        type,
        upc
    )
}