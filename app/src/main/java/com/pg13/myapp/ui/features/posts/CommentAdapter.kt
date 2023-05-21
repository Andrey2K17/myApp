package com.pg13.myapp.ui.features.posts

import com.pg13.myapp.databinding.CommentItemBinding
import com.pg13.myapp.domain.entites.Comment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pg13.myapp.R

class CommentAdapter :
    ListAdapter<Comment, CommentAdapter.CommentViewHolder>(CommentDiffCallback) {

    class CommentViewHolder(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.comment_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = getItem(position)
        holder.binding.comment = comment
    }
}

object CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }
}