package com.michaelmccormick.gmbn.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaelmccormick.gmbn.R
import com.michaelmccormick.gmbn.adapter.VideoListAdapter
import com.michaelmccormick.gmbn.viewmodel.VideoListViewModel
import kotlinx.android.synthetic.main.fragment_video_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment which lists all of the videos from the GMBN YouTube channel.
 */
class VideoListFragment : Fragment() {
    private val viewModel: VideoListViewModel by viewModel()
    private val adapter = VideoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        videoList.layoutManager = layoutManager
        videoList.adapter = adapter
        viewModel.videos.observe(this, Observer {
            if (it.isNotEmpty()) progressBar.visibility = View.GONE
            adapter.submitList(it)
        })
    }
}