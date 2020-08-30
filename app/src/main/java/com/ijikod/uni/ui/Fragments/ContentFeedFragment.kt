package com.ijikod.uni.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.databinding.ContentFeedFragmentLayoutBinding
import com.ijikod.uni.di.Injection
import com.ijikod.uni.presentation.ContentViewModel
import com.ijikod.uni.presentation.FeedViewModel
import com.ijikod.uni.ui.DataAdapter

/**
 * UI [Fragment] to display list data
 * **/
class ContentFeedFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var retryButton: Button

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var adapter: DataAdapter
    private lateinit var fab: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedViewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(FeedViewModel::class.java)

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


    /**
     * Initialise layout views from [ContentFeedFragmentLayoutBinding]
     * **/
    private fun initScreenItems(binding: ContentFeedFragmentLayoutBinding){
        recyclerView = binding.dataList
        progressBar = binding.progressBar
        retryButton = binding.retryButton
        fab = binding.fab

        // Show empty content screen from add button
        fab.setOnClickListener {
            showContentScreen(UniModel(title = "", description = "", entity = ""))
        }
    }


    /**
     * Observing data changes from view model and showing in recycler view
     * **/
    private fun initSubscribers(){
        feedViewModel.uniData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    adapter = it.data?.let { it1 ->
                        DataAdapter(it1, showContent = { selectedData ->
                            showContentScreen(selectedData)
                        })
                    }!!

                    recyclerView.adapter = adapter
                    showList()
                }

                is Resource.Error -> {
                    showErrorBtn()
                }
            }
        })
    }

    // Show loading on screen
    private fun showLoading(){
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        retryButton.visibility = View.GONE
    }

    // Show data in recycler view
    private fun showList(){
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        retryButton.visibility = View.GONE
    }

    // show Error
    private fun showErrorBtn(){
        retryButton.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
    }


    private fun showContentScreen(dataItem: UniModel){
        val contentVM = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(ContentViewModel::class.java)
        contentVM.setSelectedData(dataItem)
        val action = ContentFeedFragmentDirections.actionContentFeedFragmentToContentFragment()
        findNavController().navigate(action)
    }
}