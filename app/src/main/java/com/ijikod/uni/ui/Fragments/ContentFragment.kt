package com.ijikod.uni.ui.Fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ijikod.uni.R
import com.ijikod.uni.databinding.ContentLayoutBinding
import com.ijikod.uni.di.Injection
import com.ijikod.uni.presentation.ViewModel

/** Fragment to show content data **/
class ContentFragment: Fragment() {

    lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(ViewModel::class.java)
        setHasOptionsMenu(true)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.content_layout, container,false)
        binding.vm = viewModel

        return binding.root
    }
}