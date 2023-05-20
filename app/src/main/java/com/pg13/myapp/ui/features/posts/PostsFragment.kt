package com.pg13.myapp.ui.features.posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentPostsBinding
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.ui.base.ViewBindingFragment
import com.pg13.myapp.utils.launchOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : ViewBindingFragment<FragmentPostsBinding>() {
    private val viewModel: PostsViewModel by viewModels()

    private val adapter: PostAdapter by lazy {
        PostAdapter(this::adapterOnClick, this::adapterOnClickFavorite)
    }

    private val adapterFavorite: PostAdapter by lazy {
        PostAdapter(this::adapterOnClick, this::adapterOnClickFavorite)
    }

    private val concatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(adapter)
    }

    override fun getLayoutId() = R.layout.fragment_posts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = concatAdapter


            loadAllBtn.setOnClickListener {
                if (!concatAdapter.adapters.contains(adapter)) {
                    viewModel.updatePosts()
                    concatAdapter.addAdapter(adapter)
                }
                if (concatAdapter.adapters.contains(adapterFavorite)) {
                    concatAdapter.removeAdapter(adapterFavorite)
                }
            }

            loadFavoriteBtn.setOnClickListener {
                if (!concatAdapter.adapters.contains(adapterFavorite)) {
                    viewModel.updatePosts(true)
                    concatAdapter.addAdapter(adapterFavorite)
                }

                if (concatAdapter.adapters.contains(adapter)) {
                    concatAdapter.removeAdapter(adapter)
                }
            }

            refreshLayout.setOnRefreshListener {
                viewModel.updatePosts()
            }
        }

        viewLifecycleOwner.launchOnLifecycle {
            viewModel.posts.collect { res ->
                when (res) {
                    is Resource.Success -> {
                        if (viewModel.isFavorite) {
                            adapterFavorite.submitList(res.data)
                        } else {
                            adapter.submitList(res.data)
                        }
                        binding.refreshLayout.isRefreshing = false
                    }

                    is Resource.Loading -> {
                        binding.refreshLayout.isRefreshing = true
                    }

                    is Resource.Error -> {
                        binding.refreshLayout.isRefreshing = false
                    }
                }
            }
        }
    }

    private fun adapterOnClick(post: Post) {
        Log.d("test123", "postClick: $post")
    }

    private fun adapterOnClickFavorite(post: Post) {
        Log.d("test123", "postClickFavorite: $post")
        viewModel.addPostFavorite(post)
    }
}