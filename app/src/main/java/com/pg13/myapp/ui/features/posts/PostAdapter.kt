package com.pg13.myapp.ui.features.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pg13.myapp.R
import com.pg13.myapp.databinding.PostItemBinding
import com.pg13.myapp.domain.entites.Post

class PostAdapter(private val onClick: (Post) -> Unit, private val onClickFavorite: (Post) -> Unit) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback) {

    class PostViewHolder(val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.post_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.post = post
        holder.binding.root.setOnClickListener { onClick(post) }
        holder.binding.favoriteCtv.setOnClickListener {
            val favorite = post.favorite
            holder.binding.favoriteCtv.setCheckMarkDrawable(if (favorite) R.drawable.add_favorite else R.drawable.favorite)
            onClickFavorite(post.also { it.favorite = !it.favorite })
        }
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
}