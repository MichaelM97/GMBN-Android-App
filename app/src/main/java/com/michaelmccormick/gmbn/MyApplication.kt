package com.michaelmccormick.gmbn

import android.app.Application
import com.michaelmccormick.gmbn.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(Modules().getModule())
        }
    }
}