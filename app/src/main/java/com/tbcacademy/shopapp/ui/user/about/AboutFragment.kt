package com.tbcacademy.shopapp.ui.user.about

import android.os.Bundle
import android.view.View
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.hideBottomNavigation
import com.tbcacademy.shopapp.databinding.AboutFragmentBinding

class AboutFragment : BaseFragment<AboutFragmentBinding>(AboutFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
    }


}