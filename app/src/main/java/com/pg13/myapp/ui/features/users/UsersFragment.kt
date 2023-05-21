package com.pg13.myapp.ui.features.users

import com.pg13.myapp.R
import com.pg13.myapp.databinding.FragmentUsersBinding
import com.pg13.myapp.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : ViewBindingFragment<FragmentUsersBinding>() {
    override fun getLayoutId() = R.layout.fragment_users
}