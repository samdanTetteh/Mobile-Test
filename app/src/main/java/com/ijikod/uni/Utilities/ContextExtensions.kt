package com.ijikod.uni.Utilities

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText


/** Fragment extension function to **/
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/**
 * Hide soft keyboard from screen
 * **/
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}