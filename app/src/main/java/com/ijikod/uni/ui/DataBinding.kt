package com.ijikod.uni.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.ijikod.uni.R


/**
 * A [BindingAdapter] to show error validation msgs
 * **/
@BindingAdapter("app:titleErrorText")
fun setTitleErrorMessage(view: TextInputLayout, errorMessage: String) {
    if (errorMessage.isNotEmpty())
        view.error = view.context.getString(R.string.title_error)
    else
        view.error = errorMessage
}


/**
 * A [BindingAdapter] to show error validation msgs
 * **/
@BindingAdapter("app:descErrorText")
fun setDescErrorMessage(view: TextInputLayout, errorMessage: String) {
    if (errorMessage.isNotEmpty())
        view.error = view.context.getString(R.string.body_error)
    else
        view.error = errorMessage
}