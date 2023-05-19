package com.pg13.myapp.ui.features.posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentPostsBinding
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostsFragment : ViewBindingFragment<FragmentPostsBinding>() {
    private val viewModel: PostsViewModel by viewModels()

    private val adapter: PostAdapter by lazy {
        PostAdapter(this::adapterOnClick, this::adapterOnClickFavorite)
    }

    override fun getLayoutId() = R.layout.fragment_posts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect { res ->
                    Log.d("test123", "res: $res")
                    when (res) {
                        is Resource.Success -> {
                            adapter.submitList(res.data)
                        }
                        is Resource.Loading -> {}
                        is Resource.Error -> {}
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
    }
}