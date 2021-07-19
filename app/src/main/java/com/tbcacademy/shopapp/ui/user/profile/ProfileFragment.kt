package com.tbcacademy.shopapp.ui.user.profile

import android.os.Bundle
import android.view.View
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.hideBottomNavigation
import com.tbcacademy.shopapp.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
    }

}