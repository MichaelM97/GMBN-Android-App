package com.michaelmccormick.gmbn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.michaelmccormick.gmbn.R
import com.michaelmccormick.gmbn.viewmodel.VideoListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoListFragment : Fragment() {
    private val viewModel: VideoListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }
}