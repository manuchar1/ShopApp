package com.tbcacademy.shopapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.databinding.FragmentHomeBinding
import com.tbcacademy.shopapp.main.MainActivity
import com.tbcacademy.shopapp.menu.MenuAdapter
import com.tbcacademy.shopapp.model.Menu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    lateinit var adapter: MenuAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDrawerMenu()

        binding.btnBuy.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_loginFragment)
            if (requireActivity() is MainActivity) {
                (activity as MainActivity?)?.hideBottomNavigationView()
            }
        }
    }

    private fun initDrawerMenu() {
        adapter = MenuAdapter(
            arrayOf(
                Menu(R.id.action_loginFragment_to_navigation_home, "My Profile"),
                Menu(R.id.action_loginFragment_to_navigation_home, "My Posts")

            )
        )
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = adapter
        /* adapter.menuItemOnClick = {
             *//*findNavController().navigate(it)*//*

           *//* val navController = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navController.navController.navigate(R.id.action_global_loginFragment)*//*
        }*/

    }


}