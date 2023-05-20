package com.pg13.myapp.ui.features.users

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentUsersBinding
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.ui.base.ViewBindingFragment
import com.pg13.myapp.ui.features.posts.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : ViewBindingFragment<FragmentUsersBinding>() {
    override fun getLayoutId() = R.layout.fragment_users

    private val adapter: PostAdapter by lazy {
        PostAdapter(this::adapterOnClick, this::adapterOnClickFavorite)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.adapter = adapter
        }
    }

    private fun adapterOnClick(post: Post) {
        Log.d("test123", "postClick: $post")
    }

    private fun adapterOnClickFavorite(post: Post) {
        Log.d("test123", "postClickFavorite: $post")
    }
}