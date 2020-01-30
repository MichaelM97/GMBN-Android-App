package com.michaelmccormick.gmbn.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.michaelmccormick.gmbn.model.Video
import kotlinx.android.synthetic.main.video_list_item.view.*

/**
 * ViewHolder for Video recycler view elements.
 */
class VideoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindVideo(video: Video) {
        view.videoTitle.text = video.title
        // TODO: Use picasso to load in thumbnail
    }
}