package com.michaelmccormick.gmbn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.network.YouTubeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * The ViewModel for the VideoDetailFragment.
 */
class VideoDetailViewModel(private val repository: YouTubeRepository) : ViewModel() {

    var duration: MutableLiveData<String> = MutableLiveData("")

    private val videos: List<Video> = repository.getVideos()

    /**
     * Returns the Video associated with the passed [videoId].
     */
    fun getVideoWithId(videoId: String): Video {
        val video = videos.filter { it.id == videoId }
        assert(video.size == 1)
        return video[0]
    }

    /**
     * Fetches the duration of the video associated with the passed [videoId], and updates
     * [duration] with the result.
     */
    fun getVideoDuration(videoId: String) {
        GlobalScope.launch {
            duration.postValue(repository.getVideoDuration(videoId))
        }
    }
}