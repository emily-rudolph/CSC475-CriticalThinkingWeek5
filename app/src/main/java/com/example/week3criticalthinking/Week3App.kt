package com.example.week3criticalthinking
import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.request.crossfade


class Week3App: Application(), SingletonImageLoader.Factory {
    companion object {
        private lateinit var instance: Week3App

    }

    override fun newImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}