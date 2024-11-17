package com.example.week3criticalthinking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week3criticalthinking.databinding.FragmentTaskContainerBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecycleViewAdapter(private val imageList: List<String?>, val context: Context): RecyclerView.Adapter<RecycleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val imageBinding = FragmentTaskContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecycleViewHolder(imageBinding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val imageItem = imageList[position]
        val getPhotos: GetPhotos = GetPhotos(context)

        fun allImageData() = runBlocking {
            launch {
                getPhotos.fetchImage(holder.imageView, imageItem!!)
            }
        }
        allImageData()
    }
}