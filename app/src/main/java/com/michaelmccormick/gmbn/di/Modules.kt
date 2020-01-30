package com.michaelmccormick.gmbn.di

import com.michaelmccormick.gmbn.network.YouTubeRepository
import com.michaelmccormick.gmbn.network.YouTubeService
import com.michaelmccormick.gmbn.viewmodel.VideoDetailViewModel
import com.michaelmccormick.gmbn.viewmodel.VideoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Declares DI modules for Koin.
 */
class Modules {

    /**
     * Returns the only DI module used by this application.
     */
    fun getModule(): Module {
        return module {
            viewModel { VideoDetailViewModel(get()) }
            viewModel { VideoListViewModel(get()) }
            single { YouTubeRepository(get()) }
            single { YouTubeService() }
        }
    }
}