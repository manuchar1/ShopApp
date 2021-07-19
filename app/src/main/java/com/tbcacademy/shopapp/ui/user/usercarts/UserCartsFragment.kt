package com.tbcacademy.shopapp.ui.user.usercarts

import android.os.Bundle
import android.view.View
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.hideBottomNavigation
import com.tbcacademy.shopapp.databinding.UserCartsFragmentBinding

class UserCartsFragment :
    BaseFragment<UserCartsFragmentBinding>(UserCartsFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
    }


}