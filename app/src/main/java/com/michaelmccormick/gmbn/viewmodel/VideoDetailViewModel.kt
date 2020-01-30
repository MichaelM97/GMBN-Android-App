package com.michaelmccormick.gmbn.viewmodel

import androidx.lifecycle.ViewModel
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.network.YouTubeRepository

/**
 * The ViewModel for the VideoDetailFragment.
 */
class VideoDetailViewModel(repository: YouTubeRepository) : ViewModel() {

    private val videos: List<Video> = repository.getVideos()

    /**
     * Returns the Video associated with the passed [videoId].
     */
    fun getVideoWithId(videoId: String): Video {
        val video = videos.filter { it.id == videoId }
        assert(video.size == 1)
        return video[0]
    }
}