package com.michaelmccormick.gmbn.model.mappers

import com.google.api.services.youtube.model.PlaylistItemListResponse
import com.michaelmccormick.gmbn.model.Video

/**
 * Mapper for PlaylistItemListResponse.
 */
class PlaylistItemListResponseMapper {
    /**
     * Maps the passed PlaylistItemListResponse to a list of Video's.
     */
    fun mapToVideoList(response: PlaylistItemListResponse): List<Video> {
        val videos: MutableList<Video> = mutableListOf()
        response.items.forEach {
            videos.add(Video(it.id, it.snippet.title, it.snippet.thumbnails.default.url))
        }
        return videos
    }
}