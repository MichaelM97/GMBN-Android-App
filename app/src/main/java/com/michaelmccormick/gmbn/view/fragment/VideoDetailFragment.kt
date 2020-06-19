package com.michaelmccormick.gmbn.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.michaelmccormick.gmbn.R
import com.michaelmccormick.gmbn.viewmodel.VideoDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_video_detail.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment which displays the details of the selected video.
 */
class VideoDetailFragment : Fragment() {
    private val viewModel: VideoDetailViewModel by viewModel()
    private val args: VideoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe duration live data
        viewModel.duration.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                view.progressBar.visibility = View.GONE
                view.duration.text = getString(R.string.video_duration, it.removeRange(0, 2))
            }
        })
        viewModel.getVideoDuration(args.videoID)

        // Show known video fields
        val video = viewModel.getVideoWithId(args.videoID)
        Picasso.get().load(video.thumbnailUrl).into(view.thumbnail)
        view.videoTitle.text = video.title
        view.description.text = video.description
        view.publishedAt.text = getString(R.string.video_published_at, video.publishedAt)
    }
}