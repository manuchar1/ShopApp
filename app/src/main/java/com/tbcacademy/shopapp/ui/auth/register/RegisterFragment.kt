package com.tbcacademy.shopapp.ui.auth.register

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.base.snackbar
import com.tbcacademy.shopapp.databinding.RegisterFragmentBinding
import com.tbcacademy.shopapp.ui.auth.AuthViewModel
import com.tbcacademy.shopapp.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding>(RegisterFragmentBinding::inflate) {

   // private val viewModel by viewModels<AuthViewModel>()
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)


        subscribeToObservers()

        binding.btnRegister.setOnClickListener {
            viewModel.register(
                binding.regEmail.text.toString(),
                binding.regUsername.text.toString(),
                binding.regPassword.text.toString(),
                binding.confirmPassword.text.toString()

            )
        }

    }

    private fun subscribeToObservers() {
        viewModel.registerStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.registerProgress.isVisible = false
                snackbar(it)

            },
            onLoading = {
                binding.registerProgress.isVisible = true

            }

        ) {
            snackbar(getString(R.string.success_registration))

        })
    }

}