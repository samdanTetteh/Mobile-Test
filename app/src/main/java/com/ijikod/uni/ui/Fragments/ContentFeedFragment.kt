package com.ijikod.uni.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.databinding.ContentFeedFragmentLayoutBinding
import com.ijikod.uni.di.Injection
import com.ijikod.uni.presentation.ViewModel
import com.ijikod.uni.ui.DataAdapter

/**
 * UI [Fragment] to display list data
 * **/
class ContentFeedFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var retryButton: Button

    private lateinit var viewModel: ViewModel
    private lateinit var adapter: DataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(ViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContentFeedFragmentLayoutBinding = ContentFeedFragmentLayoutBinding.inflate(inflater, container, false)

        initScreenItems(binding)
        initSubscribers()
        return binding.root
    }


    private fun initScreenItems(binding: ContentFeedFragmentLayoutBinding){
        recyclerView = binding.dataList
        progressBar = binding.progressBar
        retryButton = binding.retryButton
    }


    private fun initSubscribers(){
        viewModel.uniData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    adapter = it.data?.let { it1 ->
                        DataAdapter(it1, showContent = { selectedData ->
                            showContentScreen(selectedData)
                        })
                    }!!

                    recyclerView.adapter = adapter
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }

                is Resource.Error -> {
                    retryButton.visibility = View.VISIBLE
                }
            }

        })
    }


    private fun showContentScreen(dataItem: UniModel){

    }
}