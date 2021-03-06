package com.tbcacademy.shopapp.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.data.UserPreference
import com.tbcacademy.shopapp.databinding.SplashFragmentBinding
import com.tbcacademy.shopapp.main.NavigationActivity

class SplashFragment : BaseFragment<SplashFragmentBinding>(SplashFragmentBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var preference: UserPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.animationView.playAnimation()
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                openFragment()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })


    }

    fun openFragment() {

        preference = UserPreference(requireContext())

        if (preference.token() == "login") {
            Intent(requireContext(), NavigationActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }

        } else {
            findNavController().navigate(R.id.action_splashFragment2_to_loginFragment)
        }

    }


}