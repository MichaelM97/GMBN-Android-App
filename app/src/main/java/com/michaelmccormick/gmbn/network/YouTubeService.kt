package com.michaelmccormick.gmbn.network

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.model.mappers.PlaylistItemListResponseMapper

/**
 * Interfaces with the YouTube API.
 */
class YouTubeService {
    private val service: YouTube
    private val jsonFactory = JacksonFactory.getDefaultInstance()

    init {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        service = YouTube.Builder(httpTransport, jsonFactory, null)
            .setApplicationName(APPLICATION_NAME)
            .build()
    }

    /**
     * Fetches all uploads for the GMBN YouTube channel.
     */
    fun getUploads(): List<Video> {
        val uploads: MutableList<Video> = mutableListOf()
        var nextPageToken: String? = null

        do {
            // Fetch the next 50 videos
            val request = service.playlistItems().list(SNIPPET)
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

    companion object {
        private const val API_KEY = "AIzaSyB8jKnTv1i-VklvGmmPrd1_py58DeA46hs"
        private const val APPLICATION_NAME = "GMBN"
        private const val UPLOADS_PLAYLIST_ID = "UU_A--fhX5gea0i4UtpD99Gg"
        private const val SNIPPET = "snippet"
        private const val MAX_RESULTS = 50L
    }
}