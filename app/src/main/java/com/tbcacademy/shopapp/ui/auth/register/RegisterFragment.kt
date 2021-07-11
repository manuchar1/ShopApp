package com.tbcacademy.shopapp.ui.auth.register

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
import com.tbcacademy.shopapp.base.snackbar
import com.tbcacademy.shopapp.databinding.RegisterFragmentBinding
import com.tbcacademy.shopapp.ui.auth.AuthViewModel
import com.tbcacademy.shopapp.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding>(RegisterFragmentBinding::inflate) {


    val viewModel: AuthViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spannableSentence()
        subscribeToObservers()

        binding.btnRegister.setOnClickListener {
            viewModel.register(
                binding.regEmail.text.toString(),
                binding.regName.text.toString(),
                binding.regPassword.text.toString(),
                binding.regConfirmPassword.text.toString()

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
            binding.registerProgress.isVisible = false
            snackbar(getString(R.string.success_registration))
            //findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        })
    }
    private fun spannableSentence() {
        val spannable = SpannableString(getString(R.string.if_already_a_member))
        val fcsBlue = ForegroundColorSpan(Color.BLUE)
        spannable.setSpan(fcsBlue, 17, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        val clickable: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
        spannable.setSpan(clickable, 17, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvGotoLogin.movementMethod = LinkMovementMethod.getInstance()
        binding.tvGotoLogin.text = spannable

    }

}