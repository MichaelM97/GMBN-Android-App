package com.michaelmccormick.gmbn.network

import com.michaelmccormick.gmbn.model.Video

/**
 * Repository used for fetching and caching data from the YouTubeAPI.
 */
class YouTubeRepository(private val service: YouTubeService) {
    private var videos: List<Video> = emptyList()

    /**
     * Returns a list videos from memory if it exists, otherwise fetches from the API.
     */
    fun getVideos(): List<Video> {
        if (videos.isEmpty()) {
            videos = service.getUploads()
        }
        return videos
    }
}