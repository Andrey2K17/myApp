package com.pg13.myapp.ui.features

import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentMainBinding
import com.pg13.myapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayoutResource() = R.layout.fragment_main
}