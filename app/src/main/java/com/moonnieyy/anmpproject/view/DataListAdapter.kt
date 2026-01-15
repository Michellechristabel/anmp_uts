package com.moonnieyy.anmpproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonnieyy.anmpproject.databinding.ItemDataBinding
//import com.moonnieyy.anmpproject.model.DataUkur
import com.moonnieyy.anmpproject.model.Ukur

class DataListAdapter(private var dataList: ArrayList<Ukur>) :
    RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {
    
    class DataViewHolder(val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.txtAge.text = data.age.toString()
        holder.binding.txtHeight.text = data.height.toString()
        holder.binding.txtWeight.text = data.weight.toString()
    }

    fun updateDataList(newList: List<Ukur>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}
