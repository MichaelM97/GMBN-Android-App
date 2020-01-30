package com.michaelmccormick.gmbn.view.viewholder

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.view.fragment.VideoListFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_list_item.view.*

/**
 * ViewHolder for Video recycler view elements.
 */
class VideoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindVideo(video: Video) {
        view.setOnClickListener { navigateToVideoDetails(it, video) }
        view.videoTitle.text = video.title
        Picasso.get().load(video.thumbnailUrl).into(view.thumbnail)
    }

    private fun navigateToVideoDetails(view: View, video: Video) {
        val direction =
            VideoListFragmentDirections.actionVideoListFragmentToVideoDetailFragment(video.id)
        view.findNavController().navigate(direction)
    }
}