package com.tbcacademy.shopapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.databinding.FragmentHomeBinding
import com.tbcacademy.shopapp.main.MainActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuy.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_loginFragment)
            if (requireActivity() is MainActivity) {
                (activity as MainActivity?)?.hideBottomNavigationView()
            }
        }
    }


}