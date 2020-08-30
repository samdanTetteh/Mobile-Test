package com.ijikod.uni.ui

import android.graphics.Typeface
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.ijikod.uni.data.Model.UniModel

class DataBinding {

    /**
     * A [BindingAdapter] to show HTML text
     * **/
    @BindingAdapter("app:titleTxt")
    fun loadText(textWidget: TextInputEditText, text: String){
        textWidget.setText(text)
        textWidget.setTypeface(null, Typeface.BOLD)
    }
}