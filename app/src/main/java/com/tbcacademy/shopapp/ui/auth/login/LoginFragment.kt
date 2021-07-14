package com.tbcacademy.shopapp.ui.auth.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.databinding.LoginFragmentBinding
import com.tbcacademy.shopapp.main.MainActivity
import com.tbcacademy.shopapp.ui.auth.AuthViewModel
import com.tbcacademy.shopapp.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    val viewModel: AuthViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
        spannableSentence()
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun subscribeToObservers() {
        viewModel.loginStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {},
            onLoading = { binding.loginProgress.isVisible = true },
        ) {
            binding.loginProgress.isVisible = false
            findNavController().navigate(R.id.action_loginFragment_to_navHomeFragment)
           /* if (requireActivity() is MainActivity) {
                (activity as MainActivity?)?.showBottomNavigationView()
            }*/

        })


    }

    private fun spannableSentence() {
        val spannable = SpannableString(getString(R.string.if_not_account_register))
        val fcsBlue = ForegroundColorSpan(Color.BLUE)
        spannable.setSpan(fcsBlue, 10, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        val clickable: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        spannable.setSpan(clickable, 10, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
        binding.tvRegister.text = spannable

    }
}