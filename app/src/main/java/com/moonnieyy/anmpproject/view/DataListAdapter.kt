package com.moonnieyy.anmpproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonnieyy.anmpproject.databinding.ItemDataBinding

class DataListAdapter(private var dataList: ArrayList<String>) :
    RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {
    
    class DataViewHolder(val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.txtItem.text = dataList[position]
    }

    fun updateDataList(newList: List<String>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}
