package com.pg13.myapp.ui.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pg13.myapp.R
import com.pg13.myapp.databinding.PhotoItemBinding
import com.pg13.myapp.entities.PhotoUI

class PhotoAdapter: ListAdapter<PhotoUI, PhotoAdapter.PhotoViewHolder>(PhotoDiffCallback) {

    class PhotoViewHolder(val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.photo_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.binding.photo = getItem(position)
    }
}

object PhotoDiffCallback : DiffUtil.ItemCallback<PhotoUI>() {
    override fun areItemsTheSame(oldItem: PhotoUI, newItem: PhotoUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PhotoUI, newItem: PhotoUI): Boolean {
        return oldItem.id == newItem.id
    }
}