package com.pg13.myapp.ui.features.posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentPostDetailsBinding
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.entities.PostUI
import com.pg13.myapp.ui.base.ViewBindingFragment
import com.pg13.myapp.utils.launchOnLifecycle
import com.pg13.myapp.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsDetailsFragment : ViewBindingFragment<FragmentPostDetailsBinding>() {
    private val viewModel: PostDetailViewModel by viewModels()

    private val args: PostsDetailsFragmentArgs by navArgs()

    override fun getLayoutId() = R.layout.fragment_post_details

    private val adapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postArg: PostUI = args.post

        binding.apply {
            recyclerView.adapter = adapter

            post = postArg
            viewModel.updateComment(postArg.id)

            (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            viewLifecycleOwner.launchOnLifecycle {
                viewModel.comments.collect { res ->
                    when (res) {
                        is Resource.Success -> {
                            Log.d("test123", "data: ${res.data}")
                            adapter.submitList(res.data)
                            binding.refreshLayout.isRefreshing = false
                        }

                        is Resource.Loading -> {
                            binding.refreshLayout.isRefreshing = true
                        }

                        is Resource.Error -> {
                            binding.refreshLayout.isRefreshing = false
                            res.message?.let { showErrorDialog(it) }
                        }
                    }
                }
            }

            refreshLayout.setOnRefreshListener {
                viewModel.updateComment(postArg.id)
            }
        }
    }

}