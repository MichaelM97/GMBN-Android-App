package com.michaelmccormick.gmbn.network

import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.model.mappers.PlaylistItemListResponseMapper

/**
 * Interfaces with the YouTube API.
 */
class YouTubeService {
    private lateinit var service: YouTube
    private val jsonFactory = JacksonFactory.getDefaultInstance()

    /**
     * This needs to be called BEFORE interacting with the service.
     * Required because when Koin is creating this class the JKS keystore is not available,
     * so GoogleNetHttpTransport.newTrustedTransport() throws an exception.
     */
    fun initialise() {
        if (!this::service.isInitialized) {
            val httpTransport = com.google.api.client.http.javanet.NetHttpTransport()
            service = YouTube.Builder(httpTransport, jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .build()
        }
    }

    /**
     * Fetches all uploads for the GMBN YouTube channel.
     */
    fun getUploads(): List<Video> {
        val uploads: MutableList<Video> = mutableListOf()
        var nextPageToken: String? = null

        do {
            // Fetch the next 50 videos
            val request = service.playlistItems().list(SNIPPET_AND_CONTENT_DETAILS)
            val response = request.setKey(API_KEY)
                .setPlaylistId(UPLOADS_PLAYLIST_ID)
                .setMaxResults(MAX_RESULTS)
                .setPageToken(nextPageToken)
                .execute()

            // Save the next page token for the next loop
            nextPageToken = response.nextPageToken

            // Map response to custom Video object
            uploads.addAll(PlaylistItemListResponseMapper().mapToVideoList(response))
        } while (nextPageToken != null)

        return uploads
    }

    /**
     * Fetches the duration of the video associated with the passed [videoId].
     */
    fun getVideoDuration(videoId: String): String {
        val request = service.videos().list(CONTENT_DETAILS)
        val response = request.setKey(API_KEY)
            .setId(videoId)
            .execute()
        return response.items[0].contentDetails.duration
    }

    companion object {
        private const val API_KEY = "AIzaSyB8jKnTv1i-VklvGmmPrd1_py58DeA46hs"
        private const val APPLICATION_NAME = "GMBN"
        private const val UPLOADS_PLAYLIST_ID = "UU_A--fhX5gea0i4UtpD99Gg"
        private const val SNIPPET_AND_CONTENT_DETAILS = "snippet, contentDetails"
        private const val CONTENT_DETAILS = "contentDetails"
        private const val MAX_RESULTS = 50L
    }
}