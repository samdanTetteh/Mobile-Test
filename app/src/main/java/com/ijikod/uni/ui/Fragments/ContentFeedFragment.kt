package com.ijikod.uni.ui.Fragments

import android.os.Bundle
import android.os.Handler
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
import com.ijikod.uni.Utilities.hideKeyboard
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
    private lateinit var contentVM: ContentViewModel
    private lateinit var adapter: DataAdapter
    private lateinit var fab: FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedViewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(FeedViewModel::class.java)

        contentVM = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireActivity())).get(ContentViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContentFeedFragmentLayoutBinding = ContentFeedFragmentLayoutBinding.inflate(inflater, container, false)
        val view = binding.root


        initScreenItems(binding)
        initSubscribers()
        return view
    }


    /**
     * Initialise layout views from [ContentFeedFragmentLayoutBinding]
     * **/
    private fun initScreenItems(binding: ContentFeedFragmentLayoutBinding){
        recyclerView = binding.dataList
        progressBar = binding.progressBar
        retryButton = binding.retryButton
        fab = binding.fab

        adapter = DataAdapter(showContent = { selectedData -> showContentScreen(selectedData) })
        recyclerView.adapter = adapter

        // Show empty content screen from add button
        fab.setOnClickListener {
            showContentScreen(UniModel(description = "", entity = ""))
        }
    }


    /**
     * Observing data changes from view model and showing in recycler view
     * **/
    private fun initSubscribers(){
        feedViewModel.data()
        feedViewModel.data.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    // Setting list data to data recycler view adapter
                    it.data?.let { dataSet -> adapter.setDataSet(dataSet) }
                    showList()
                }

                is Resource.Error -> {
                    showErrorBtn()
                }
            }
        })


        // Scroll list to the top once new data is added
        contentVM.isAddData.observe(viewLifecycleOwner, Observer {
            when(it){
                true -> {
                    Handler().postDelayed({ recyclerView.smoothScrollToPosition(0) }, 1000)
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

    /**
     * Navigate to content screen with selected [UniModel] data item
     */
    private fun showContentScreen(dataItem: UniModel){
        contentVM.setSelectedData(dataItem)
        contentVM.isAddData.value = false
        val action = ContentFeedFragmentDirections.actionContentFeedFragmentToContentFragment()
        findNavController().navigate(action)
    }
}