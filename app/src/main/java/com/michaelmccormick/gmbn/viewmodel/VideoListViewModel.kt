package com.michaelmccormick.gmbn.viewmodel

import androidx.lifecycle.ViewModel
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.network.YouTubeRepository

class VideoListViewModel (private val repository: YouTubeRepository): ViewModel() {

    private val videos: List<Video> = repository.getVideos()


}