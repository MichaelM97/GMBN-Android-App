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
        service.initialise()
        if (videos.isEmpty()) {
            videos = service.getUploads()
        }
        return videos
    }

    /**
     * Returns the duration of the video associated with the passed [videoId].
     * Does not cache the result in memory.
     */
    fun getVideoDuration(videoId: String): String {
        service.initialise()
        return service.getVideoDuration(videoId)
    }
}