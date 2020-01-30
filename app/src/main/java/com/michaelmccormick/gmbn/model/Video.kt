package com.michaelmccormick.gmbn.model

/**
 * Custom representation for a video.
 */
data class Video(
    val id: String,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val publishedAt: String
)