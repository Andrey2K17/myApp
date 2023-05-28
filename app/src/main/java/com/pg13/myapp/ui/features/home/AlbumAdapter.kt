package com.pg13.myapp.ui.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pg13.myapp.R
import com.pg13.myapp.databinding.AlbumItemBinding
import com.pg13.myapp.entities.AlbumUI

class AlbumAdapter: ListAdapter<AlbumUI, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback) {

    class AlbumViewHolder(val binding: AlbumItemBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.album_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.album = getItem(position)
    }
}

object AlbumDiffCallback : DiffUtil.ItemCallback<AlbumUI>() {
    override fun areItemsTheSame(oldItem: AlbumUI, newItem: AlbumUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AlbumUI, newItem: AlbumUI): Boolean {
        return oldItem.id == newItem.id
    }
}