package com.tbcacademy.shopapp.ui.auth.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.base.BaseFragment
import com.tbcacademy.shopapp.databinding.LoginFragmentBinding

class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spannableSentence()
    }

    private fun spannableSentence() {
        val spannable = SpannableString(getString(R.string.if_not_account_register))
        val fcsBlue = ForegroundColorSpan(Color.BLUE)
        spannable.setSpan(fcsBlue, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        val clickable: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        spannable.setSpan(clickable, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
        binding.tvRegister.text = spannable

    }

}