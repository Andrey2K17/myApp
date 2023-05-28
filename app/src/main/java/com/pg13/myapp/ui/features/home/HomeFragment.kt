package com.pg13.myapp.ui.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentHomeBinding
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.ui.base.ViewBindingFragment
import com.pg13.myapp.utils.launchOnLifecycle
import com.pg13.myapp.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    override fun getLayoutId() = R.layout.fragment_home

    private val adapter by lazy {
        HomeAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.adapter = adapter

            refreshLayout.setOnRefreshListener {
                viewModel.update()
            }
        }

        viewLifecycleOwner.launchOnLifecycle {
            viewModel.items.collect { res ->
                when (res) {
                    is Resource.Success -> {
                        val list = res.data

                        adapter.submitList(list)
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
    }
}