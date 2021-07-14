package com.tbcacademy.shopapp.base

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.utils.Resource


fun Fragment.snackbar(text: String) {
    Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show()
}

inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {

    return try {
        action()
    } catch (e: Exception) {
        Resource.Error(e.message ?: "An unknown error occured")
    }
}

fun ImageView.setImage(url: String?) {

    if (!url.isNullOrEmpty()) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_placeholder_image).into(this)
    } else {
        setImageResource(R.drawable.ic_placeholder_image)

    }
}


fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.goneIf(isGone: Boolean) = if (isGone) gone() else show()

fun View.hideIf(isHide: Boolean) = if (isHide) hide() else show()
