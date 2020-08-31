package com.ijikod.uni.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ijikod.uni.R
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.databinding.ListItemLayoutBinding

class DataAdapter(val showContent : (dataItem : UniModel) -> Unit) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var data  = listOf<UniModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data[position]
        with(holder){
            binding(dataItem)
        }
    }

    fun setDataSet(listData : List<UniModel>){
        data = listData
        notifyDataSetChanged()
    }


    /**
     * [ViewHolder] class to show data in recyclerView.
     * **/
    inner class ViewHolder(itemViewBinding: ListItemLayoutBinding) : RecyclerView.ViewHolder(itemViewBinding.root){
        val entityTxt = itemViewBinding.entityTxt
        val descpTxt = itemViewBinding.detailsTxt
        val bgImage = itemViewBinding.cardBgImg


        fun binding(dataItem: UniModel){
            entityTxt.text = dataItem.entity
            descpTxt.text = dataItem.description
            Glide.with(bgImage.context).load(bgImage.context.getDrawable(R.drawable.uni)).into(bgImage)

            //Navigate to details screen
            this.itemView.setOnClickListener {
                showContent(dataItem)
            }
        }

    }

}



