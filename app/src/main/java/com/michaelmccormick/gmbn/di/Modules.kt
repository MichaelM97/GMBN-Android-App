package com.michaelmccormick.gmbn.di

import com.michaelmccormick.gmbn.network.YouTubeRepository
import com.michaelmccormick.gmbn.network.YouTubeService
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
            single { YouTubeRepository(get()) }
            single { YouTubeService() }
        }
    }
}