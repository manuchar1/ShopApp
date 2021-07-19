package com.tbcacademy.shopapp.ui.user.userposts

import android.os.Bundle
import android.view.View
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.hideBottomNavigation
import com.tbcacademy.shopapp.databinding.UserPostsFragmentBinding

class UserPostsFragment :
    BaseFragment<UserPostsFragmentBinding>(UserPostsFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
    }


}