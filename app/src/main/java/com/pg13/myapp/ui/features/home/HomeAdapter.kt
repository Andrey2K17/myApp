package com.pg13.myapp.ui.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pg13.myapp.R
import com.pg13.myapp.databinding.ViewRcyHorizontalBinding
import com.pg13.myapp.domain.entites.AlbumItem
import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.entites.PhotoItem
import com.pg13.myapp.mappers.mapToUI
import com.pg13.myapp.ui.recyclerView.HorizontalSpaceItemDecoration
import com.pg13.myapp.utils.genericCastOrNull
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class HomeAdapter @Inject constructor(@ActivityContext val context: Context) :
    ListAdapter<ItemHome, HomeAdapter.ViewHolder>(HomeDiffCallback) {

    companion object {
        private const val ALBUM = 1
        private const val PHOTO = 2
    }

    open class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        open fun setItem(data: ItemHome) {}
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AlbumItem -> ALBUM
            is PhotoItem -> PHOTO
            else -> throw RuntimeException("Not support item ${getItem(position)}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ALBUM -> genericCastOrNull(
                AlbumViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.view_rcy_horizontal,
                        parent,
                        false
                    )
                )
            )

            PHOTO -> genericCastOrNull(
                PhotoViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.view_rcy_horizontal,
                        parent,
                        false
                    )
                )
            )

            else -> throw RuntimeException("Not support type=$viewType")
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    inner class AlbumViewHolder(val binding: ViewRcyHorizontalBinding) : ViewHolder(binding) {
        private var isItemDecoration = false
        private var adapter: AlbumAdapter? = null
        override fun setItem(data: ItemHome) {
            super.setItem(data)
            if (adapter == null) {
                adapter = AlbumAdapter()
                binding.rcv.adapter = adapter
            }
            if (!isItemDecoration) {
                isItemDecoration = true
                binding.rcv.addItemDecoration(
                    HorizontalSpaceItemDecoration(
                        context.resources.getDimensionPixelSize(
                            R.dimen.padding_10dp
                        )
                    )
                )
            }

            adapter?.submitList((data as? AlbumItem)?.list?.map { it.mapToUI() })
        }
    }

    inner class PhotoViewHolder(val binding: ViewRcyHorizontalBinding) : ViewHolder(binding) {
        private var isItemDecoration = false
        private var adapter: PhotoAdapter? = null
        override fun setItem(data: ItemHome) {
            super.setItem(data)
            if (adapter == null) {
                adapter = PhotoAdapter()
                binding.rcv.adapter = adapter
            }
            if (!isItemDecoration) {
                isItemDecoration = true
                binding.rcv.addItemDecoration(
                    HorizontalSpaceItemDecoration(
                        context.resources.getDimensionPixelSize(
                            R.dimen.padding_10dp
                        )
                    )
                )
            }

            adapter?.submitList((data as? PhotoItem)?.list?.map { it.mapToUI() })
        }
    }
}

object HomeDiffCallback : DiffUtil.ItemCallback<ItemHome>() {
    override fun areItemsTheSame(oldItem: ItemHome, newItem: ItemHome): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemHome, newItem: ItemHome): Boolean {
        return oldItem.id == newItem.id
    }
}
