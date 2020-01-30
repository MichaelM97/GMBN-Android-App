package com.michaelmccormick.gmbn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.michaelmccormick.gmbn.R
import com.michaelmccormick.gmbn.model.Video
import com.michaelmccormick.gmbn.view.viewholder.VideoViewHolder

/**
 * RecyclerView adapter for VideoListFragment.
 */
class VideoListAdapter : ListAdapter<Video, VideoViewHolder>(VideoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.apply {
            holder.bindVideo(video)
            itemView.tag = video
        }
    }
}

private class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}