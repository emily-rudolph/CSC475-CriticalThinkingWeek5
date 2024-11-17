package com.example.week3criticalthinking

import android.content.Context
import android.util.Log
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.ImageRequest
import okhttp3.OkHttpClient
import android.widget.ImageView
import coil3.request.crossfade
import coil3.request.target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request
import kotlinx.serialization.*
import kotlinx.serialization.json.*

// API Key 4n67rzJtA5jZVmw30T0S3Ua0CAI18RyEmyvhSbZWctyQPpp6VgL1oQZd

class GetPhotos(context: Context) {

    private val client = OkHttpClient().newBuilder().build()

    private val imageLoader = ImageLoader.Builder(context)
        .components {
            add(
                OkHttpNetworkFetcherFactory(
                    callFactory = {
                        OkHttpClient()
                    }
                )
            )
        }
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    fun fetchImage(imageView: ImageView, url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("APIreq", "Preparing Request...")
                val request = Request.Builder()
                    .url(url)
                    .addHeader(
                        "Authorization",
                        "4n67rzJtA5jZVmw30T0S3Ua0CAI18RyEmyvhSbZWctyQPpp6VgL1oQZd"
                    )
                    .build()
                Log.d("APIreq", "Executing Request...")
                val response = client.newCall(request).execute()
                Log.d("APIreq", "Response Body: ${response.body}")

                if (!response.isSuccessful) {
                    Log.e("APIreq", "Failed to Fetch JSON from API: ${response.message}")
                }

                Log.d("APIreq", "After Request")

                val body = response.body?.string()
                if (body.isNullOrBlank()) {
                    Log.e("APIreq", "Received Blank Response Body")
                }

                Log.d("Decode", "Before JSON Decode")
                val photo = Json.decodeFromString<PhotoObj>(body!!)
                val imageurl = photo.src.large

                Log.d("ImageURL", imageurl)

                val imageReq = ImageRequest.Builder(imageView.context)
                    .data(imageurl)
                    .crossfade(true)
                    .target(imageView)
                    .build()
                imageLoader.enqueue(imageReq)
                Log.d("FetchImage", "after response!")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ErrorFetching", "Error While Getting Photo: ${e.message}")
                imageView.setImageResource(R.drawable.baseline_block_24) //

                withContext(Dispatchers.Main) {
                    Log.e("ErrorFetching", "Error While Getting Photo: ${e.message}")
                }
            }
        }
    }
}