package com.michaelmccormick.gmbn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.network.YouTubeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * The ViewModel for the VideoListFragment.
 */
class VideoListViewModel(private val repository: YouTubeRepository) : ViewModel() {

    var videos: MutableLiveData<List<Video>> = MutableLiveData(emptyList())

    init {
        GlobalScope.launch {
            videos.postValue(repository.getVideos())
        }
    }
}