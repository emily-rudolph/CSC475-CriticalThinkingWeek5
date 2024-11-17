package com.example.week3criticalthinking

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.week3criticalthinking.databinding.FragmentTaskContainerBinding

class RecycleViewHolder(val imageBinding: FragmentTaskContainerBinding): RecyclerView.ViewHolder(imageBinding.root) {
    val imageView: ImageView = imageBinding.root.findViewById(R.id.imageView)
}