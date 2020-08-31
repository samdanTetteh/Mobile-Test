package com.ijikod.uni.ui.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ijikod.uni.R
import com.ijikod.uni.Utilities.hideKeyboard
import com.ijikod.uni.Utilities.showKeyboard
import com.ijikod.uni.databinding.ContentLayoutBinding
import com.ijikod.uni.di.Injection
import com.ijikod.uni.presentation.ContentViewModel


/** Fragment to show content data **/
class ContentFragment: Fragment() {

    lateinit var contentViewModel: ContentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentViewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(ContentViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onPause() {
        super.onPause()

        // Hide keyboard is still displaying
        hideKeyboard()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.content_layout, container,false)
        binding.vm = contentViewModel

        val editText = binding.titleEntityTxt
        editText.requestFocus()
        editText.showKeyboard()
        return binding.root
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_data -> {
                if (contentViewModel.isFormValid()){
                    contentViewModel.saveData()

                    // navigate back to feed screen
                    findNavController().navigateUp()
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

}