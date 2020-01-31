package com.michaelmccormick.gmbn.model.mappers

import android.text.format.DateFormat
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
            videos.add(
                Video(
                    it.contentDetails.videoId,
                    it.snippet.thumbnails.high.url,
                    it.snippet.title,
                    it.snippet.description,
                    DateFormat.format("dd-MM-yyyy kk:mma", it.snippet.publishedAt.value).toString()
                )
            )
        }
        return videos
    }
}