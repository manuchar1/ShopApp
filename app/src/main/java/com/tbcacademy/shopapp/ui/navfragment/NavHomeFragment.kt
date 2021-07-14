package com.tbcacademy.shopapp.ui.navfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.databinding.FragmentNavHomeBinding

class NavHomeFragment : BaseFragment<FragmentNavHomeBinding>(FragmentNavHomeBinding::inflate) {

    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)

        binding.drawerNavView.setupWithNavController(navController)


    }


}